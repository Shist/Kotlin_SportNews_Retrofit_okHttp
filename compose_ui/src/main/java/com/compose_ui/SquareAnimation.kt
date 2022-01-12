package com.composeui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlin.math.roundToInt

@Composable
fun MakeAnimationSquare() {
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(Size.Zero) }
    Box(
        Modifier.fillMaxSize()
            .onSizeChanged { size = it.toSize() }
    ) {
        Box(
            Modifier.offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
                .size(50.dp)
                .background(Color.Blue)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val original = Offset(offsetX.value, offsetY.value)
                        val summed = original + dragAmount
                        val newValue = Offset(
                            x = summed.x.coerceIn(0f, size.width - 50.dp.toPx()),
                            y = summed.y.coerceIn(0f, size.height - 50.dp.toPx())
                        )
                        offsetX.value = newValue.x
                        offsetY.value = newValue.y
                    }
                }
        )
    }
}
