package br.com.ricardomorsch.viewpagerindicator

import android.content.Context
import android.widget.FrameLayout

/**
 * @Ricardo Morsch
 */

abstract class ViewPagerIndicatorView(context: Context) : FrameLayout(context) {
    abstract fun select()
    abstract fun unselect()
}