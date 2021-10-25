package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import io.navendra.retrofitkotlindeferred.R
import kotlin.math.cos
import kotlin.math.sin

class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context!!, attrs) {

    private val anglesNumber =
        context?.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomImage)?.
        getInteger(R.styleable.NewsItemCustomImage_attrAnglesNumber, 4)

    private val figureAngle = 360.0 / anglesNumber!!.toDouble()

    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        path.moveTo(w / 2.0f, 0.0f) // Ставим точку наверх в середину

        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw (canvas: Canvas) {

        var figureAnglePointX: Double
        var figureAnglePointY: Double

        for (angleNum in 1..anglesNumber!!) {
            val neededAngle = figureAngle * angleNum

            figureAnglePointX = (width / 2) * (1 + sin(Math.toRadians(neededAngle)))
            figureAnglePointY = (height / 2) * (1 + cos(Math.toRadians((neededAngle + 180.0))))

            path.lineTo(
                figureAnglePointX.toFloat(),
                figureAnglePointY.toFloat()
            )
        }

        path.close()
        canvas.clipPath(path)

        super .onDraw (canvas)
    }

}