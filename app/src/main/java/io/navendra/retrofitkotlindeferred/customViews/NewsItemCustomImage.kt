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

    // четные штуки - 6 (картинка слева, текст - справа)
    // нечетные штуки - 8 (картинска справа, текст - слева)
    // у recycle-а (adapter-а) нужно будет посмотреть, как делать ViewType

    private val paint = Paint()

    private val typedArray =
        context?.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomImage)
    private val anglesNumber =
        typedArray?.getInteger(R.styleable.NewsItemCustomImage_attrAnglesNumber, 4)

    private val ellipseX = DoubleArray(360)
    private val ellipseY = DoubleArray(360)

    private val path = Path()

    override  fun  onDraw (canvas: Canvas ) {

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        for (i in 0..359) {
            ellipseX[i] = width / 2 + (width / 2) * cos(Math.toRadians(i.toDouble()))
            ellipseY[i] = height / 2 + (height / 2) * sin(Math.toRadians(i.toDouble()))
        }

        path.moveTo(
            ellipseX[269].toFloat(),
            ellipseY[269].toFloat()
        )

        clip(canvas, anglesNumber!!)
        super .onDraw (canvas)
    }

    private fun clip(canvas: Canvas, anglesNumber: Int) {

        val figureAngle = 360 / anglesNumber

        var figureAnglePointX: Int
        var figureAnglePointY: Int

        path.fillType = FillType.EVEN_ODD

        for (i in 1..anglesNumber) {
            val neededAngle = (figureAngle * i + 269) % 360

            figureAnglePointX = ellipseX[neededAngle].toInt()
            figureAnglePointY = ellipseY[neededAngle].toInt()
            
            path.lineTo(
                figureAnglePointX.toFloat(),
                figureAnglePointY.toFloat()
            )
        }
        path.close()
        canvas.clipPath(path)

    }

}