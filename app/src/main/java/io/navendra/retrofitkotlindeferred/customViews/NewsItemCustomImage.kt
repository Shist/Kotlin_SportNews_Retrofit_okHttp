package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.graphics.Path.FillType


class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    private val paint = Paint()
    private val n =
        resources.getInteger(io.navendra.retrofitkotlindeferred.R.integer.n_for_angles_number)

    override  fun  onDraw (canvas: Canvas ) {
        drawNAngle(canvas, n)
        super .onDraw (canvas)
    }

    private fun drawNAngle(canvas: Canvas, n: Int) {

        val imageWidth = canvas.width
        val imageHeight = canvas.height

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        val ellipseX = DoubleArray(360)
        val ellipseY = DoubleArray(360)

        for (i in 0..359) {
            ellipseX[i] = (imageWidth/2) + (imageWidth/2)*kotlin.math.cos(Math.toRadians(i.toDouble()))
            ellipseY[i] = (imageHeight/2) + (imageHeight/2)*kotlin.math.sin(Math.toRadians(i.toDouble()))
        }

        var figureAngle = 360/n

        if (figureAngle > 360) {
            figureAngle = 360
        }

        var figureAnglePointX = 0
        var figureAnglePointY = 0

        val path = Path()
        path.fillType = FillType.EVEN_ODD

        path.moveTo(
            ellipseX[269].toInt().toFloat(),
            ellipseY[269].toInt().toFloat()
        )

        for (i in 1..n) {
            if (figureAngle*i+270-1 <= 359) {
                figureAnglePointX = ellipseX[figureAngle * i + 270 - 1].toInt()
                figureAnglePointY = ellipseY[figureAngle * i + 270 - 1].toInt()
            }
            if (figureAngle*i+270-1 > 359) {
                figureAnglePointX = ellipseX[figureAngle * i - 90 - 1].toInt()
                figureAnglePointY = ellipseY[figureAngle * i - 90 - 1].toInt()
            }
            path.lineTo(
                figureAnglePointX.toFloat(),
                figureAnglePointY.toFloat()
            )
        }
        path.close()
        canvas.clipPath(path)

    }

}