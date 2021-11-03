package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.navendra.retrofitkotlindeferred.R

class NewsItemCustomLayout(context: Context?, attrs: AttributeSet?) :
    FrameLayout(context!!, attrs) {

    private val evenItem =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getBoolean(R.styleable.NewsItemCustomLayout_even_item, false)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val itemWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val itemHeight = itemWidth / 2
        this.setMeasuredDimension(itemWidth, itemHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val viewWidth = width
        val viewHeight = height

        val imgView = getChildAt(0)
        val altTextView = getChildAt(1)
        val headlineView = getChildAt(2)

        val imgLeft: Int
        val imgTop: Int
        val imgRight: Int
        val imgBottom: Int

        val altTextLeft: Int
        val altTextTop: Int
        val altTextRight: Int
        val altTextBottom: Int

        val headlineLeft: Int
        val headlineTop: Int
        val headlineRight: Int
        val headlineBottom: Int

        if (evenItem) {
            imgLeft = this.paddingLeft
            imgTop = this.paddingTop
            imgRight = viewWidth / 2 - this.paddingRight
            imgBottom = viewHeight - this.paddingBottom

            altTextLeft = viewWidth / 2 + this.paddingLeft
            altTextTop = this.paddingTop
            altTextRight = viewWidth - this.paddingRight
            altTextBottom = viewHeight / 2 - this.paddingBottom

            headlineLeft = viewWidth / 2 + this.paddingLeft
            headlineTop = viewHeight / 2 + this.paddingTop
            headlineRight = viewWidth - this.paddingRight
            headlineBottom = viewHeight - this.paddingBottom
        }
        else {
            imgLeft = viewWidth / 2 + this.paddingLeft
            imgTop = this.paddingTop
            imgRight = viewWidth - this.paddingRight
            imgBottom = viewHeight - this.paddingBottom

            altTextLeft = this.paddingLeft
            altTextTop = this.paddingTop
            altTextRight = viewWidth / 2 - this.paddingRight
            altTextBottom = viewHeight / 2 - this.paddingBottom

            headlineLeft = this.paddingLeft
            headlineTop = viewHeight / 2 + this.paddingTop
            headlineRight = viewWidth / 2 - this.paddingRight
            headlineBottom = viewHeight - this.paddingBottom
        }

        val imgWidth = imgRight - imgLeft
        val imgHeight = imgBottom - imgTop

        val altTextWidth = altTextRight - altTextLeft
        val altTextHeight = altTextBottom - altTextTop

        val headlineWidth = headlineRight - headlineLeft
        val headlineHeight = headlineBottom - headlineTop

        imgView.measure(
            MeasureSpec.makeMeasureSpec(imgWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(imgHeight, MeasureSpec.EXACTLY)
        )

        altTextView.measure(
            MeasureSpec.makeMeasureSpec(altTextWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(altTextHeight, MeasureSpec.AT_MOST)
        )

        headlineView.measure(
            MeasureSpec.makeMeasureSpec(headlineWidth, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(headlineHeight, MeasureSpec.AT_MOST)
        )

        imgView.layout(imgLeft, imgTop, imgRight, imgBottom)

        altTextView.layout(
            altTextLeft,
            headlineTop - altTextView.measuredHeight,
            altTextLeft + altTextView.measuredWidth,
            headlineTop
        )

        headlineView.layout(
            headlineLeft,
            headlineTop,
            headlineLeft + headlineView.measuredWidth,
            headlineTop + headlineView.measuredHeight
        )

    }


}