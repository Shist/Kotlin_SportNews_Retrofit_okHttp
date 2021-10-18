package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.graphics.Path.FillType


class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    // четные штуки - 6 (картинка слева, текст - справа)
    // нечетные штуки - 8 (картинска справа, текст - слева)
    // у recycle-а (adapter-а) нужно будет посмотреть, как делать ViewType

    private val paint = Paint()
    private val n =
        resources.getInteger(io.navendra.retrofitkotlindeferred.R.integer.n_for_angles_number)

    override  fun  onDraw (canvas: Canvas ) {
        clip(canvas, n)
        super .onDraw (canvas)
    }

    private val ellipseX = DoubleArray(360)
    private val ellipseY = DoubleArray(360)

    private val path = Path()

    private fun clip(canvas: Canvas, n: Int) {

        val imageWidth = canvas.width
        val imageHeight = canvas.height

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        // Вынести за функцию, но как-то передать туда ширину и высоту
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

        path.reset() // Чистим его
        path.fillType = FillType.EVEN_ODD

        // Тоже можно только один раз посчитать
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