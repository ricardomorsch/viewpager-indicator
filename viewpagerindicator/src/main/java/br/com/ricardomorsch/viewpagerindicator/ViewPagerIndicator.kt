package br.com.ricardomorsch.viewpagerindicator

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.LinearLayout

class ViewPagerIndicator : LinearLayout, ViewPager.OnPageChangeListener {

    private lateinit var viewPager: ViewPager
    private var indicatorClass: String = ""
    private var currentSelection = -1

    constructor(context: Context) : super(context) {
        setup(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, defStyle)
    }

    private fun setup(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, defStyle, 0)
        indicatorClass = typedArray.getString(R.styleable.ViewPagerIndicator_indicatorClass)
        if (indicatorClass.isEmpty())
            throw IllegalStateException("attribute indicator class is empty")
        typedArray.recycle()
    }

    fun attachViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        this.viewPager.addOnPageChangeListener(this)

        removeAllViews()

        this.viewPager.adapter?.let {
            addIndicators(it.count)
            currentSelection = this.viewPager.currentItem
            select(currentSelection)
        }
    }


    private fun addIndicators(count: Int) {
        val clazz = Class.forName(indicatorClass)
        val constructor = clazz.getConstructor(Context::class.java)

        for (index in 0 until count) {
            val indicatorView = constructor.newInstance(context) as ViewPagerIndicatorView
            indicatorView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            addView(indicatorView)
            indicatorView.unselect()
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        unselect(currentSelection)
        currentSelection = position
        select(currentSelection)
    }

    private fun select(position: Int) {
        (getChildAt(position) as? ViewPagerIndicatorView)?.select()
    }

    private fun unselect(position: Int) {
        (getChildAt(position) as? ViewPagerIndicatorView)?.unselect()
    }

}
