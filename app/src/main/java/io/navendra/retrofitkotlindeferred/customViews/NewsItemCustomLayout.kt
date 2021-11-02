package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.navendra.retrofitkotlindeferred.R

class NewsItemCustomLayout(context: Context?, attrs: AttributeSet?) :
    FrameLayout(context!!, attrs) {

    private val even_item =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getBoolean(R.styleable.NewsItemCustomLayout_even_item, false)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        val viewWidth = width
        val viewHeight = viewWidth / 2

        val imgLeft = this.paddingLeft
        val imgTop = this.paddingTop
        val imgRight = viewWidth / 2 - this.paddingRight
        val imgBottom = viewHeight - this.paddingBottom
        val imgWidth = imgRight - imgLeft
        val imgHeight = imgBottom - imgTop
        val imgView = getChildAt(0)
        imgView.measure(MeasureSpec.makeMeasureSpec(imgWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(imgHeight, MeasureSpec.EXACTLY))
        imgView.layout(imgLeft, imgTop, imgRight, imgBottom)

        val altTextLeft = viewWidth / 2 + this.paddingLeft
        val altTextTop = this.paddingTop
        val altTextRight = viewWidth - this.paddingRight
        val altTextBottom = viewHeight / 2 - this.paddingBottom
        val altTextWidth = altTextRight - altTextLeft
        val altTextHeight = altTextBottom - altTextTop
        val altTextView = getChildAt(1)
        altTextView.measure(MeasureSpec.makeMeasureSpec(altTextWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(altTextHeight, MeasureSpec.EXACTLY))
        altTextView.layout(altTextLeft, altTextTop, altTextRight, altTextBottom)

        val headlineLeft = viewWidth / 2 + this.paddingLeft
        val headlineTop = viewHeight / 2 + this.paddingTop
        val headlineRight = viewWidth - this.paddingRight
        val headlineBottom = viewHeight - this.paddingBottom
        val headlineWidth = headlineRight - headlineLeft
        val headlineHeight = headlineBottom - headlineTop
        val headlineView = getChildAt(2)
        headlineView.measure(MeasureSpec.makeMeasureSpec(headlineWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(headlineHeight, MeasureSpec.EXACTLY))
        headlineView.layout(headlineLeft, headlineTop, headlineRight, headlineBottom)

        super.onLayout(changed, left, top, right, bottom)
    }


}