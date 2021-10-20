package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.graphics.Path.FillType
import androidx.appcompat.widget.AppCompatImageView
import io.navendra.retrofitkotlindeferred.R
import kotlin.math.cos
import kotlin.math.sin

class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context!!, attrs) {

    private val typedArray =
        context?.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomImage)
    private val anglesNumber =
        typedArray?.getInteger(R.styleable.NewsItemCustomImage_attrAnglesNumber, 4)

    private val ellipseX = DoubleArray(360)
    private val ellipseY = DoubleArray(360)

    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        for (i in 0..359) {
            ellipseX[i] = (w / 2) * (1 + sin(Math.toRadians(i.toDouble())))
            ellipseY[i] = (h / 2) * (1 + cos(Math.toRadians((i + 180).toDouble()))) // + 180, т.к. нам нужно, чтобы начальная вершина была вверху, а не внизу
        }

        path.fillType = FillType.EVEN_ODD

        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw (canvas: Canvas) {

        path.reset()

        path.moveTo(
            ellipseX[0].toFloat(),
            ellipseY[0].toFloat()
        )

        clip(canvas, anglesNumber!!)

        super .onDraw (canvas)
    }

    private fun clip(canvas: Canvas, anglesNumber: Int) {

        val figureAngle = 360 / anglesNumber

        var figureAnglePointX: Double
        var figureAnglePointY: Double

        for (i in 1..anglesNumber) {
            val neededAngle = (figureAngle * i) % 360

            figureAnglePointX = ellipseX[neededAngle]
            figureAnglePointY = ellipseY[neededAngle]
            
            path.lineTo(
                figureAnglePointX.toFloat(),
                figureAnglePointY.toFloat()
            )
        }

        path.close()
        canvas.clipPath(path)
    }

}