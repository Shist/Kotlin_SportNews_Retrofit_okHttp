package com.compose_ui

import android.util.Log
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
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun MakeAnimationSquare() {
    val squareDp = 100.dp
    var scale by remember { mutableStateOf(1f)}
    var mRotation by remember { mutableStateOf(0f)}
    var offset by remember { mutableStateOf(Offset.Zero) }
    var size by remember { mutableStateOf(Size.Zero) }
    Box(
        Modifier.fillMaxSize()
            .onSizeChanged {
                size = it.toSize()
            }
    )
    {
        Box(
            Modifier
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
                            scale *= zoom
                            mRotation += rotation
                            Log.d("ROTATE_VALUE", Math.toRadians(mRotation.toDouble()).toFloat().toString())
                            val sinRotateAngle = sin(Math.toRadians(mRotation.toDouble()).toFloat())
                            val cosRotateAngle = cos(Math.toRadians(mRotation.toDouble()).toFloat())
                            val rotateRecalculatingValue = Offset(
                                x = pan.x * cosRotateAngle - pan.y * sinRotateAngle,
                                y = pan.y * cosRotateAngle + pan.x * sinRotateAngle,
                            )
                            offset += rotateRecalculatingValue
                            val putInBoundsValue = Offset(
                                x = offset.x.coerceIn(0f, size.width - squareDp.toPx()),
                                y = offset.y.coerceIn(0f, size.height - squareDp.toPx())
                            )
                            offset = putInBoundsValue
                        }
                    )
                }
                .background(Color.Green)
                .size(squareDp)
        )
    }
}
