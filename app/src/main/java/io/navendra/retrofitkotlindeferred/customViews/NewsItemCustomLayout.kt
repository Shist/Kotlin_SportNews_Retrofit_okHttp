package io.navendra.retrofitkotlindeferred.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import io.navendra.retrofitkotlindeferred.R
import android.view.View
import android.util.TypedValue
import kotlin.math.roundToInt


class NewsItemCustomLayout(context: Context?, attrs: AttributeSet?) :
    FrameLayout(context!!, attrs) {

    private val defaultCellSize = 48f

    private var cellSize = 0f

    private val columns =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getInteger(R.styleable.NewsItemCustomLayout_columns, 0)
    private val spacing =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getDimension(R.styleable.NewsItemCustomLayout_spacing, 0F)
    private val leftAttr =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getInteger(R.styleable.NewsItemCustomLayout_layout_left, 0)
    private val topAttr =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getInteger(R.styleable.NewsItemCustomLayout_layout_top, 0)
    private val widthAttr =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getInteger(R.styleable.NewsItemCustomLayout_layout_cellsWidth, -1)
    private val heightAttr =
        context!!.obtainStyledAttributes(attrs, R.styleable.NewsItemCustomLayout).
        getInteger(R.styleable.NewsItemCustomLayout_layout_cellsHeight, -1)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width: Int
        val height: Int

        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            cellSize =
                ((measuredWidth - paddingLeft - paddingRight).toFloat() / columns)
        } else {
            cellSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, defaultCellSize, resources
                    .displayMetrics
            )
            width = ((columns * cellSize).toInt())
        }

        val childCount = childCount
        var child: View

        var maxRow = 0

        for (i in 0 until childCount) {
            child = getChildAt(i)


            val bottomOfSize = topAttr + heightAttr

            val childWidthSpec = MeasureSpec.makeMeasureSpec(
                ((widthAttr * cellSize).toInt() - spacing * 2).toInt(),
                MeasureSpec.EXACTLY
            )
            val childHeightSpec = MeasureSpec.makeMeasureSpec(
                ((heightAttr * cellSize).toInt() - spacing * 2).toInt(),
                MeasureSpec.EXACTLY
            )
            child.measure(childWidthSpec, childHeightSpec)
            if (bottomOfSize > maxRow) {
                maxRow = bottomOfSize
            }
        }

        val measuredHeight = ((maxRow * cellSize).roundToInt() + paddingTop + paddingBottom)
        height = when (heightMode) {
            MeasureSpec.EXACTLY ->
                MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.AT_MOST ->
                MeasureSpec.getSize(heightMeasureSpec).coerceAtMost(measuredHeight)
            else -> measuredHeight
        }

        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

        super.onLayout(changed, left, top, right, bottom)

        val childCount = childCount

        var child: View

        for (i in 0 until childCount) {
            child = getChildAt(i)

            //TODO columns, spacing, leftAttr, topAttr, widthAttr, heightAttr
            //не успевают получить значения и имеют ненужные дефолтные

            val topOnLayout =
                (topAttr * cellSize) + paddingTop + spacing
            val leftOnLayout =
                (leftAttr * cellSize) + paddingLeft + spacing
            val rightOnLayout =
                ((leftAttr + widthAttr) * cellSize) + paddingLeft - spacing
            val bottomOnLayout =
                ((topAttr + heightAttr) * cellSize) + paddingTop - spacing

            child.layout(
                topOnLayout.toInt(),
                leftOnLayout.toInt(),
                rightOnLayout.toInt(),
                bottomOnLayout.toInt())
        }
    }

}