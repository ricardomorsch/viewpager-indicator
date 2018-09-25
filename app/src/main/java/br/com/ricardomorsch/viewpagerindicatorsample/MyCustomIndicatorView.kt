package br.com.ricardomorsch.viewpagerindicatorsample

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.widget.LinearLayout
import br.com.ricardomorsch.viewpagerindicator.ViewPagerIndicatorView

/**
 * Ricardo Morsch
 */

class MyCustomIndicatorView(context: Context) : ViewPagerIndicatorView(context) {

    companion object {
        private const val SELECTED_COLOR = Color.DKGRAY
        private const val UNSELECTED_COLOR = Color.LTGRAY
        private const val ANIMATION_DURATION = 200L
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.item_viewpager_indicator, this, true)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun select() = animateColor(UNSELECTED_COLOR, SELECTED_COLOR)

    override fun unselect() = animateColor(SELECTED_COLOR, UNSELECTED_COLOR)

    private fun animateColor(fromColor: Int, toColor: Int) {
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor)
        valueAnimator.duration = ANIMATION_DURATION
        valueAnimator.addUpdateListener { animator ->
            (getChildAt(0).background as GradientDrawable).setColorFilter(animator.animatedValue as Int, PorterDuff.Mode.SRC_IN)
        }
        valueAnimator.start()
    }
}