package br.com.ricardomorsch.viewpagerindicatorsample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SampleAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val count = 15

    override fun getItem(position: Int): Fragment {
        return EmptyFragment.newInstance("Fragment ${position + 1}")
    }

    override fun getCount(): Int {
        return count
    }
}
