package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView

class NewsItemCustomImage(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    override  fun  onDraw (canvas: Canvas ) {
        // вызов метода super, чтобы сохранить любой рисунок с родительской стороны.
        super .onDraw (canvas)

        drawPentagon(canvas)
    }

    private fun drawPentagon(canvas: Canvas) {

        val width = canvas.width.toFloat ()
        val height = canvas.height.toFloat ()
        val shapeBounds = RectF ( 0f , 0f , width, height)
        val paint = Paint ()

        paint.color = Color.BLACK

        canvas.drawRect (shapeBounds, paint)

    }

}

//TODO
// https://www.raywenderlich.com/9556022-drawing-custom-shapes-in-android