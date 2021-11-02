package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.Display
import android.widget.FrameLayout
import io.navendra.retrofitkotlindeferred.R
import android.view.WindowManager

class NewsItemCustomLayout(context: Context?, attrs: AttributeSet?) :
    FrameLayout(context!!, attrs) {

    private var itemWidth = 0
    private var itemHeight = 0

    init {
        val display: Display =
            (context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val deviceDisplay = Point()
        display.getSize(deviceDisplay)
        itemWidth = deviceDisplay.x
        itemHeight = itemWidth / 2
    }

    private val even_item =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getBoolean(R.styleable.NewsItemCustomLayout_even_item, false)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        val imgLeft = this.paddingLeft
        val imgTop = this.paddingTop
        val imgRight = itemWidth / 2 - this.paddingRight
        val imgBottom = itemHeight - this.paddingBottom
        val imgWidth = imgRight - imgLeft
        val imgHeight = imgBottom - imgTop
        val imgView = getChildAt(0)
        imgView.measure(MeasureSpec.makeMeasureSpec(imgWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(imgHeight, MeasureSpec.AT_MOST))
        imgView.layout(imgLeft, imgTop, imgRight, imgBottom)

        val altTextLeft = itemWidth / 2 + this.paddingLeft
        val altTextTop = this.paddingTop
        val altTextRight = itemWidth - this.paddingRight
        val altTextBottom = itemHeight / 2 - this.paddingBottom
        val altTextWidth = altTextRight - altTextLeft
        val altTextHeight = altTextBottom - altTextTop
        val altTextView = getChildAt(1)
        altTextView.measure(MeasureSpec.makeMeasureSpec(altTextWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(altTextHeight, MeasureSpec.AT_MOST))
        altTextView.layout(altTextLeft, altTextTop, altTextRight, altTextBottom)

        val headlineLeft = itemWidth / 2 + this.paddingLeft
        val headlineTop = itemHeight / 2 + this.paddingTop
        val headlineRight = itemWidth - this.paddingRight
        val headlineBottom = itemHeight - this.paddingBottom
        val headlineWidth = headlineRight - headlineLeft
        val headlineHeight = headlineBottom - headlineTop
        val headlineView = getChildAt(2)
        headlineView.measure(MeasureSpec.makeMeasureSpec(headlineWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(headlineHeight, MeasureSpec.AT_MOST))
        headlineView.layout(headlineLeft, headlineTop, headlineRight, headlineBottom)

        super.onLayout(changed, left, top, right, bottom)
    }


}