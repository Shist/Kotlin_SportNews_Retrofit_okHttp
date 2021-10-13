package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.graphics.Path.FillType


class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    private val paint = Paint()

    override  fun  onDraw (canvas: Canvas ) {

        val n = 9
        drawNAngle(canvas, n)

        super .onDraw (canvas)

    }

    private fun drawNAngle(canvas: Canvas, n: Int) {

        val imageWidth = canvas.width
        val imageHeight = canvas.height

        val perimeter = 2*imageWidth + 2*imageHeight

        val anglesDistance = perimeter/n

        //imageWidth = 518
        //imageHeight = 291
        //perimeter = 1618
        //angles_distance = 323.6 (for pentagon)

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        var currentSum = imageWidth/2
        var x = 0
        var y = 0

        val path = Path()
        path.fillType = FillType.EVEN_ODD

        path.moveTo(imageWidth/2.toFloat(), 0.toFloat())
        for (i in 1..n) {
            currentSum += anglesDistance
            if (currentSum > perimeter) {
                currentSum -= perimeter
            }
            if (currentSum in 0..imageWidth) {
                x = currentSum
                y = 0
            }
            if (currentSum in imageWidth..(imageWidth+imageHeight)) {
                x = imageWidth
                y = currentSum - imageWidth
            }
            if (currentSum in (imageWidth+imageHeight)..(2*imageWidth+imageHeight)) {
                x = imageWidth-(currentSum-(imageWidth+imageHeight))
                y = imageHeight
            }
            if (currentSum in (2*imageWidth+imageHeight)..perimeter) {
                x = 0
                y = imageHeight-(currentSum-(2*imageWidth+imageHeight))
            }
            path.lineTo(
                x.toFloat(),
                y.toFloat()
            )
        }

        path.close()

        canvas.clipPath(path)

    }

}