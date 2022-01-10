package com.compose_ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun MakeAnimationSquare() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .size(100.dp)
                .swipeToMove(),
            color = Color.DarkGray
        ) {

        }
    }
}

private fun Modifier.swipeToMove(): Modifier = composed {
    // TODO 6-1: Create an Animatable instance for the offset of the swiped element.
    val offsetX = remember { Animatable(0f) } // Add this line
    val offsetY = remember { Animatable(0f) } // NEW
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
                offsetY.stop() // NEW
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
            }
        }
    }

        // Apply the offset to the element.
        .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
}
