package br.com.ricardomorsch.viewpagerindicator

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Ricardo Morsch
 */

class ViewPagerIndicator : LinearLayout, ViewPager.OnPageChangeListener {

    private var indicatorClass: String = ""
    private var selectedPosition = -1

    constructor(context: Context) : super(context) {
        setIndicatorClass(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setIndicatorClass(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setIndicatorClass(attrs, defStyle)
    }

    private fun setIndicatorClass(attrs: AttributeSet?, defStyle: Int) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, defStyle, 0)

        try {
            indicatorClass = typedArray.getString(R.styleable.ViewPagerIndicator_indicatorClass)
        } catch (e: IllegalStateException) {
            throw IllegalStateException("Attribute indicatorClass not defined in ViewPagerIndicator layout")
        } finally {
            typedArray.recycle()
        }
    }

    fun attachViewPager(viewPager: ViewPager) {
        removeAllViews()
        viewPager.addOnPageChangeListener(this)
        viewPager.adapter?.let {
            addIndicators(it.count)
            setStartSelection(viewPager.currentItem)
        }
    }

    private fun addIndicators(count: Int) {
        for (index in 0 until count) {
            val indicatorView = instantiateIndicator()
            addIndicatorIntoView(indicatorView)
            indicatorView.unselect()
        }
    }

    private fun addIndicatorIntoView(indicatorView: ViewPagerIndicatorView) {
        indicatorView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        addView(indicatorView)
    }

    private fun instantiateIndicator(): ViewPagerIndicatorView {
        val className = Class.forName(indicatorClass)
        val classConstructor = className.getConstructor(Context::class.java)
        return classConstructor.newInstance(context) as ViewPagerIndicatorView
    }

    private fun setStartSelection(startPosition: Int) {
        val indicatorView = getIndicator(startPosition)
        indicatorView.select()
        selectedPosition = startPosition
    }

    private fun getIndicator(position: Int): ViewPagerIndicatorView {
        val indicatorView = getChildAt(position)
        if (indicatorView is ViewPagerIndicatorView)
            return indicatorView
        else
            throw IllegalStateException("Custom indicator view must extends ViewPagerIndicatorView")
    }

    override fun onPageSelected(newSelectedPosition: Int) {

        val selectedIndicator = getIndicator(selectedPosition)
        selectedIndicator.unselect()

        val newSelectedIndicator = getIndicator(newSelectedPosition)
        newSelectedIndicator.select()

        selectedPosition = newSelectedPosition
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
}
