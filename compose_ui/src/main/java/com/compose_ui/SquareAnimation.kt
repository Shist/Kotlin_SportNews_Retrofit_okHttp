package com.compose_ui

import androidx.compose.animation.core.Animatable
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
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun MakeAnimationSquare() {
    val squareDp = 100.dp
    var scale by remember { mutableStateOf(1f) }
    var rotationAngle by remember { mutableStateOf(0f) }
    var fieldSize by remember { mutableStateOf(Size.Zero) }
    val squarePosition = remember { Animatable(
        Offset(fieldSize.width / 2,fieldSize.height / 2), Offset.VectorConverter) }
    val scopeSizeInit = rememberCoroutineScope()
    val scopeCalculateVelocity = rememberCoroutineScope()
    val scopeCalculatePosition = rememberCoroutineScope()
    Box(modifier = Modifier
        .fillMaxSize()
        .onSizeChanged {
            fieldSize = it.toSize()
            scopeSizeInit.launch {
                squarePosition.snapTo(Offset(fieldSize.width / 2, fieldSize.height / 2))
            }
        } // TODO We need to put square exactly to the center
    ) {
        Box(modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotationAngle,
                translationX = squarePosition.value.x,
                translationY = squarePosition.value.y
            )
            .pointerInput(Unit) {
                val velocityTracker = VelocityTracker()
                forEachGesture {
                    velocityTracker.resetTracking()
                    awaitPointerEventScope {
                        val fingerTap = awaitFirstDown()
                        scopeCalculateVelocity.launch {
                            squarePosition.snapTo(fingerTap.position)
                        }
                        drag(fingerTap.id) {
                            velocityTracker.addPosition(it.uptimeMillis, it.position)
                            scopeCalculateVelocity.launch {
                                squarePosition.snapTo(it.position)
                            }
                        }
                        val velocity = velocityTracker.calculateVelocity()
                        scopeCalculateVelocity.launch {
                            val initialVelocity = Offset(velocity.x, velocity.y)
                            squarePosition.animateDecay(initialVelocity, exponentialDecay())
                        }
                    }
                    detectTransformGestures(
                        panZoomLock = false,
                        onGesture = { _, pan, zoom, rotation ->
                            val rotatingBoundsOffset = scale * (squareDp.toPx() / 2) *
                                    (sqrt(2.0) * cos(Math.toRadians(45.0 - abs(rotationAngle % 90).toDouble())) - 1).toFloat()
                            if ((((scale * zoom - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset) <
                                (fieldSize.width - squareDp.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset) &&
                                (((scale * zoom - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset) <
                                (fieldSize.height - squareDp.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset)
                            ) {
                                scale *= zoom
                                if (scale < 0.5f)
                                    scale = 0.5f
                                rotationAngle += rotation
                                val sinRotateAngle =
                                    sin(
                                        Math
                                            .toRadians(rotationAngle.toDouble())
                                            .toFloat()
                                    )
                                val cosRotateAngle =
                                    cos(
                                        Math
                                            .toRadians(rotationAngle.toDouble())
                                            .toFloat()
                                    )
                                val rotateRecalculatingValue = Offset(
                                    x = scale * (pan.x * cosRotateAngle - pan.y * sinRotateAngle),
                                    y = scale * (pan.y * cosRotateAngle + pan.x * sinRotateAngle),
                                )
                                scopeCalculatePosition.launch {
                                    squarePosition.snapTo(squarePosition.value + rotateRecalculatingValue)
                                    val putInBoundsValue = Offset(
                                        x = squarePosition.value.x.coerceIn(
                                            ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                            fieldSize.width - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                        ),
                                        y = squarePosition.value.y.coerceIn(
                                            ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                            fieldSize.height - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                        ),
                                    )
                                    squarePosition.snapTo(putInBoundsValue)
                                }
                            }
                        }
                    )
                }
            }
            .background(Color.Green)
            .size(squareDp)
        )
    }
}
