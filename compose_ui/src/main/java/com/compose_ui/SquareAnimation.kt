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
    Box(modifier = Modifier.fillMaxSize()
            .onSizeChanged {
                size = it.toSize()
                offset = Offset(x = size.width / 2, // TODO We need to get density and put square exactly to the center
                    y = size.height / 2) // TODO We need to get density and put square exactly to the center
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
                            if (scale * zoom * squareDp.toPx() < size.width &&
                                scale * zoom * squareDp.toPx() < size.height)
                                scale *= zoom
                            if (scale < 0.5f)
                                scale = 0.5f
                            mRotation += rotation
                            val sinRotateAngle = sin(Math.toRadians(mRotation.toDouble()).toFloat())
                            val cosRotateAngle = cos(Math.toRadians(mRotation.toDouble()).toFloat())
                            val rotateRecalculatingValue = Offset(
                                x = scale * (pan.x * cosRotateAngle - pan.y * sinRotateAngle),
                                y = scale * (pan.y * cosRotateAngle + pan.x * sinRotateAngle),
                            )
                            offset += rotateRecalculatingValue
                            // TODO Выразить смещение границ в зависимости от угла поворота
                            val rotatingBoundsOffset = sinRotateAngle * squareDp.toPx() / 2
                            Log.d("ROTATE_BOUNDS_VALUE", rotatingBoundsOffset.toString())
                            val putInBoundsValue = Offset(
                                x = offset.x.coerceIn(
                                    (scale - 1) * squareDp.toPx() / 2 - rotatingBoundsOffset,
                                    size.width - squareDp.toPx() * ((scale + 1) / 2)) + rotatingBoundsOffset,
                                y = offset.y.coerceIn(
                                    (scale - 1) * squareDp.toPx() / 2 - rotatingBoundsOffset,
                                    size.height - squareDp.toPx() * ((scale + 1) / 2)) + rotatingBoundsOffset
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
