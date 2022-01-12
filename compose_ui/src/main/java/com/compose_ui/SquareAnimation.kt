package com.compose_ui

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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun MakeAnimationSquare() {
    var scale by remember { mutableStateOf(1f)}
    var m_rotation by remember { mutableStateOf(0f)}
    var offset by remember { mutableStateOf(Offset.Zero) }
    Box(
        Modifier
            .fillMaxSize()
    )
    {
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = m_rotation,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = false,
                        onGesture = { _, pan, zoom, rotation->
                            scale *= zoom
                            m_rotation += rotation
                            offset += pan
                        }
                    )
                }
                .background(Color.Green)
                .size(100.dp)
        )
    }
}
