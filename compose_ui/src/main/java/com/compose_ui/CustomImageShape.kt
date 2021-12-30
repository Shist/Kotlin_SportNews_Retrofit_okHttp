package com.compose_ui

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.cos
import kotlin.math.sin

class CustomImageShape(private val anglesNumber: Int) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = Path().apply { polygon(anglesNumber, size) })
    }
}

fun Path.polygon(anglesNumber: Int, size: Size) {
    val figureAngle = 360.0 / anglesNumber.toDouble()
    val itemWidth = size.width
    val itemHeight = size.height

    moveTo( // Ставим точку наверх в середину
        x = itemWidth / 2.0f,
        y = 0.0f
    )

    var figureAnglePointX: Double
    var figureAnglePointY: Double

    for (angleNum in 1..anglesNumber) {
        val neededAngle = figureAngle * angleNum

        figureAnglePointX = (itemWidth / 2) * (1 + sin(Math.toRadians(neededAngle)))
        figureAnglePointY = (itemHeight / 2) * (1 + cos(Math.toRadians((neededAngle + 180.0))))

        lineTo(
            x = figureAnglePointX.toFloat(),
            y = figureAnglePointY.toFloat()
        )
    }

    close()
}
