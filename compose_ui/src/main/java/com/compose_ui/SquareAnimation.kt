package com.compose_ui

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.launch
import kotlin.math.*

@Composable
fun MakeAnimationSquare() {
    val squareDp = 100.dp
    var scale by remember { mutableStateOf(1f) }
    var mRotation by remember { mutableStateOf(0f) }
    var position = remember { Animatable(Offset(500f,500f), Offset.VectorConverter) }
    var size by remember { mutableStateOf(Size.Zero) }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
        .onSizeChanged {
            size = it.toSize()
            position = Animatable(Offset(
                x = size.width / 2, // TODO We need to put square exactly to the center
                y = size.height / 2
            ), Offset.VectorConverter) // TODO We need to put square exactly to the center
        }
    ) {
        Box(modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = mRotation,
                translationX = position.value.x,
                translationY = position.value.y
            )
            .pointerInput(Unit) {
                val velocityTracker = VelocityTracker()
                detectTransformGestures(
                    panZoomLock = false,
                    onGesture = { _, pan, zoom, rotation ->
                        velocityTracker.resetTracking()
                        val rotatingBoundsOffset = scale * (squareDp.toPx() / 2) *
                                (sqrt(2.0) * cos(Math.toRadians(45.0 - abs(mRotation % 90).toDouble())) - 1).toFloat()
                        if ((((scale * zoom - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset) <
                            (size.width - squareDp.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset) &&
                            (((scale * zoom - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset) <
                            (size.height - squareDp.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset)
                        ) {
                            scale *= zoom
                            if (scale < 0.5f)
                                scale = 0.5f
                            mRotation += rotation
                            val sinRotateAngle =
                                sin(
                                    Math
                                        .toRadians(mRotation.toDouble())
                                        .toFloat()
                                )
                            val cosRotateAngle =
                                cos(
                                    Math
                                        .toRadians(mRotation.toDouble())
                                        .toFloat()
                                )
                            val rotateRecalculatingValue = Offset(
                                x = scale * (pan.x * cosRotateAngle - pan.y * sinRotateAngle),
                                y = scale * (pan.y * cosRotateAngle + pan.x * sinRotateAngle),
                            )
                            var putInBoundsValue : Offset
                            Log.d("XUI", "x = " + position.value.x.toString() +
                                    " ; y = " + position.value.y.toString() + "\n")
                            scope.launch {
                                position.snapTo(position.value + rotateRecalculatingValue)
                                putInBoundsValue = Offset(
                                    x = position.value.x.coerceIn(
                                        ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                        size.width - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                    ),
                                    y = position.value.y.coerceIn(
                                        ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                        size.height - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                    ),
                                )
                                position = Animatable(putInBoundsValue, Offset.VectorConverter)
                                val velocity = velocityTracker.calculateVelocity()
                                var initialVelocity = Offset(velocity.x, velocity.y)
                                do {
                                    val result =
                                        position.animateDecay(initialVelocity, exponentialDecay())
                                    initialVelocity = result.endState.velocity
                                    if (position.value.x == position.lowerBound?.x ||
                                        position.value.x == position.upperBound?.x ) {
                                        initialVelocity = initialVelocity.copy(x = -initialVelocity.x)
                                    }
                                    if (position.value.y == position.lowerBound?.y ||
                                        position.value.y == position.upperBound?.y ) {
                                        initialVelocity = initialVelocity.copy(y = -initialVelocity.y)
                                    }
                                } while (result.endReason == AnimationEndReason.BoundReached)
                            }
                        }
                    }
                )
            }
            .background(Color.Green)
            .size(squareDp)
        )
    }
}
