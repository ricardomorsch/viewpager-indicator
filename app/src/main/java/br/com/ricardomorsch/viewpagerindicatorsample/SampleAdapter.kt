package br.com.ricardomorsch.viewpagerindicatorsample

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SampleAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = EmptyFragment.newInstance("Fragment ${position + 1}")

    override fun getCount() = 15
}
