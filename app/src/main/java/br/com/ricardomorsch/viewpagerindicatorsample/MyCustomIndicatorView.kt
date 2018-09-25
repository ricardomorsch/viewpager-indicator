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


class MyCustomIndicatorView(context: Context) : ViewPagerIndicatorView(context) {

    companion object {
        private const val SELECTED_COLOR = Color.WHITE
        private const val DURATION = 200L
    }

    private var unselectedColor = Color.parseColor("#80FFFFFF")

    init {
        LayoutInflater.from(context).inflate(R.layout.item_viewpager_indicator, this, true)
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    override fun select() {
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), unselectedColor, SELECTED_COLOR)
        valueAnimator.duration = DURATION
        valueAnimator.addUpdateListener { animator ->
            (getChildAt(0).background as GradientDrawable).setColorFilter(animator.animatedValue as Int, PorterDuff.Mode.SRC_IN)

        }
        valueAnimator.start()
    }

    override fun unselect() {
        val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), SELECTED_COLOR, unselectedColor)
        valueAnimator.duration = DURATION
        valueAnimator.addUpdateListener { animator ->
            (getChildAt(0).background as GradientDrawable).setColorFilter(animator.animatedValue as Int, PorterDuff.Mode.SRC_IN)
        }
        valueAnimator.start()

    }
}