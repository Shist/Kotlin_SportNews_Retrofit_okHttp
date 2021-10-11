package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.Path.FillType
import android.util.Log


class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    override  fun  onDraw (canvas: Canvas ) {

        super .onDraw (canvas)

        drawPentagon(canvas)

    }

    private fun drawPentagon(canvas: Canvas) {

        val paint = Paint()

        val pentagonWidth = canvas.width
        val pentagonHeight = canvas.height

        //pentagonWidth = 518
        //pentagonHeight = 291

        paint.strokeWidth = 4F
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true

        val aLeftTop = Point(0, 0)
        val bLeftTop = Point(pentagonWidth/2, 0)
        val cLeftTop = Point(0, pentagonHeight/3)

        val aLeftBottom = Point(0, pentagonHeight*1)
        val bLeftBottom = Point(0, pentagonHeight/3)
        val cLeftBottom = Point(pentagonWidth/3, pentagonHeight*1)

        val aRightTop = Point(pentagonWidth*1, 0)
        val bRightTop = Point(pentagonWidth*1, pentagonHeight/3)
        val cRightTop = Point(pentagonWidth/2, 0)

        val aRightBottom = Point(pentagonWidth*1, pentagonHeight*1)
        val bRightBottom = Point(pentagonWidth*2/3, pentagonHeight*1)
        val cRightBottom = Point(pentagonWidth*1, pentagonHeight/3)

        val path = Path()
        path.fillType = FillType.EVEN_ODD

        path.moveTo(aLeftTop.x.toFloat(), aLeftTop.y.toFloat())
        path.lineTo(bLeftTop.x.toFloat(), bLeftTop.y.toFloat())
        path.lineTo(cLeftTop.x.toFloat(), cLeftTop.y.toFloat())
        path.lineTo(aLeftTop.x.toFloat(), aLeftTop.y.toFloat())

        path.moveTo(aLeftBottom.x.toFloat(), aLeftBottom.y.toFloat())
        path.lineTo(bLeftBottom.x.toFloat(), bLeftBottom.y.toFloat())
        path.lineTo(cLeftBottom.x.toFloat(), cLeftBottom.y.toFloat())
        path.lineTo(aLeftBottom.x.toFloat(), aLeftBottom.y.toFloat())

        path.moveTo(aRightTop.x.toFloat(), aRightTop.y.toFloat())
        path.lineTo(bRightTop.x.toFloat(), bRightTop.y.toFloat())
        path.lineTo(cRightTop.x.toFloat(), cRightTop.y.toFloat())
        path.lineTo(aRightTop.x.toFloat(), aRightTop.y.toFloat())

        path.moveTo(aRightBottom.x.toFloat(), aRightBottom.y.toFloat())
        path.lineTo(bRightBottom.x.toFloat(), bRightBottom.y.toFloat())
        path.lineTo(cRightBottom.x.toFloat(), cRightBottom.y.toFloat())
        path.lineTo(aRightBottom.x.toFloat(), aRightBottom.y.toFloat())

        path.close()

        canvas.drawPath(path, paint)

    }

}