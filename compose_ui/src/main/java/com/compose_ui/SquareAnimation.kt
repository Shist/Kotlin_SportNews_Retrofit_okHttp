package com.compose_ui

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.*

@Composable
fun MakeAnimationSquare() {
    val squareDp = 100.dp
    var scale by remember { mutableStateOf(1f) }
    var mRotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var size by remember { mutableStateOf(Size.Zero) }
    Box(modifier = Modifier
        .fillMaxSize()
        .onSizeChanged {
            size = it.toSize()
            offset = Offset(
                x = size.width / 2, // TODO We need to put square exactly to the center
                y = size.height / 2
            ) // TODO We need to put square exactly to the center
        }
    ) {
        Box(modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = mRotation,
                translationX = offset.x,
                translationY = offset.y
            )
            .pointerInput(Unit) {
                detectTransformGestures(
                    panZoomLock = false,
                    onGesture = { _, pan, zoom, rotation ->
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
                            offset += rotateRecalculatingValue
                            val putInBoundsValue = Offset(
                                x = offset.x.coerceIn(
                                    ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                    size.width - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                ),
                                y = offset.y.coerceIn(
                                    ((scale - 1) / 2) * squareDp.toPx() + rotatingBoundsOffset,
                                    size.height - squareDp.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                ),
                            )
                            offset = putInBoundsValue
                        }
                    }
                )
            }
            .background(Color.Green)
            .size(squareDp)
            .impulse()
        )
    }
}

private fun Modifier.impulse(
): Modifier = composed {
    // TODO 6-1: Create an Animatable instance for the offset of the swiped element.
    val offsetX = remember { Animatable(0f) } // Add this line
    val offsetY = remember { Animatable(0f) } // Add this line
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                // TODO 6-2: Touch detected; the animation should be stopped.
                offsetX.stop() // Add this line
                offsetY.stop()
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    drag(pointerId) { change ->
                        // TODO 6-3: Apply the drag change to the Animatable offset.
                        // Add these 4 lines
                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        val verticalDragOffset = offsetY.value + change.positionChange().y
                        launch {
                            offsetX.snapTo(horizontalDragOffset)
                            offsetY.snapTo(verticalDragOffset)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocityX = velocityTracker.calculateVelocity().x
                val velocityY = velocityTracker.calculateVelocity().y
                // TODO 6-4: Calculate the eventual position where the fling should settle
                //           based on the current offset value and velocity
                // Add this line
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocityX)
                val targetOffsetY = decay.calculateTargetValue(offsetY.value, velocityY)
                // TODO 6-5: Set the upper and lower bounds so that the animation stops when it
                //           reaches the edge.
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                offsetY.updateBounds(
                    lowerBound = -size.height.toFloat(),
                    upperBound = size.height.toFloat()
                )
                launch {
                    offsetX.animateTo(targetValue = targetOffsetX, initialVelocity = velocityX)
                    offsetX.animateTo(targetValue = targetOffsetY, initialVelocity = velocityY)
                }
            }
        }
    }
        // Apply the offset to the element.
        .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
}
