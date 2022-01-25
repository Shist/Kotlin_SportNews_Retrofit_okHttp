package com.compose_ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import kotlinx.coroutines.launch
import kotlin.math.*

@Composable
fun MakeAnimationSquare() {

    val position = remember { Animatable(Offset(500f,500f), Offset.VectorConverter) }
    val scope = rememberCoroutineScope()
    val smallBoxSize = 100.dp
    var offset by remember { mutableStateOf(Offset.Zero) }
    var ratationAngle by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }

    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                val velocityTracker = VelocityTracker()
                forEachGesture {
                    velocityTracker.resetTracking()
                    awaitPointerEventScope {

                        val down = awaitFirstDown()
                        scope.launch {
                            position.snapTo(down.position)
                        }

                        drag(down.id) {
                            velocityTracker.addPosition(it.uptimeMillis, it.position)
                            scope.launch {
                                position.snapTo(it.position)
                            }
                        }

                        val halfSizePx = smallBoxSize.toPx() / 2
                        position.updateBounds(
                            Offset(halfSizePx, halfSizePx),
                            Offset(size.width - halfSizePx, size.height - halfSizePx)
                        )

                        val velocity = velocityTracker.calculateVelocity()
                        scope.launch {
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
            }) {
        Box(
            Modifier
                .size(smallBoxSize)
                .rotate(ratationAngle) // You need to pay attention to the calling sequence of offset and rotate
                .scale(scale)
                .offset {
                    val halfSizePx = smallBoxSize.roundToPx() / 2
                    position.value.round() - IntOffset(halfSizePx, halfSizePx)
                }
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true, // Can I rotate when I pan or zoom in
                        onGesture = { centroid: Offset, pan: Offset, zoom: Float, rotation: Float ->
                            val rotatingBoundsOffset = scale * (smallBoxSize.toPx() / 2) *
                                    (sqrt(2.0) * cos(Math.toRadians(45.0 - abs(ratationAngle % 90).toDouble())) - 1).toFloat()
                            if ((((scale * zoom - 1) / 2) * smallBoxSize.toPx() + rotatingBoundsOffset) <
                                (size.width - smallBoxSize.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset) &&
                                (((scale * zoom - 1) / 2) * smallBoxSize.toPx() + rotatingBoundsOffset) <
                                (size.height - smallBoxSize.toPx() * ((scale * zoom + 1) / 2) - rotatingBoundsOffset)
                            ) {
//                                offset += pan
                                scale *= zoom
                                if (scale < 0.5f)
                                    scale = 0.5f
                                ratationAngle += rotation
                                val sinRotateAngle =
                                    sin(
                                        Math
                                            .toRadians(ratationAngle.toDouble())
                                            .toFloat()
                                    )
                                val cosRotateAngle =
                                    cos(
                                        Math
                                            .toRadians(ratationAngle.toDouble())
                                            .toFloat()
                                    )
                                val rotateRecalculatingValue = Offset(
                                    x = scale * (pan.x * cosRotateAngle - pan.y * sinRotateAngle),
                                    y = scale * (pan.y * cosRotateAngle + pan.x * sinRotateAngle),
                                )
                                offset += rotateRecalculatingValue
                                val putInBoundsValue = Offset(
                                    x = offset.x.coerceIn(
                                        ((scale - 1) / 2) * smallBoxSize.toPx() + rotatingBoundsOffset,
                                        size.width - smallBoxSize.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                    ),
                                    y = offset.y.coerceIn(
                                        ((scale - 1) / 2) * smallBoxSize.toPx() + rotatingBoundsOffset,
                                        size.height - smallBoxSize.toPx() * ((scale + 1) / 2) - rotatingBoundsOffset
                                    ),
                                )
                                offset = putInBoundsValue
                            }
                        }
                    )
                }
        )
    }
}
