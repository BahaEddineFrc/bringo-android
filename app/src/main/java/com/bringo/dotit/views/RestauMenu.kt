package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bringo.dotit.R
import com.google.android.material.tabs.TabLayout

class RestauMenu : Fragment(){

    private lateinit var mPager: ViewPager

    private var tabs : List<String> = listOf("Breakfast", "Lunch", "Dinner")

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         var v = inflater.inflate(R.layout.fragment_restau_menu, container, false)

         mPager = v.findViewById(R.id.pager)  // Instantiate a ViewPager and a PagerAdapter.
         val pagerAdapter = ScreenSlidePagerAdapter(fragmentManager as FragmentManager) // The pager adapter, which provides the pages to the view pager widget.
         mPager.adapter = pagerAdapter


         val tabLayout : TabLayout = v.findViewById(R.id.tab_layout)
         tabLayout.setupWithViewPager(mPager)

         return v

    }



    fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
                //super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 3
        override fun getItem(position: Int): Fragment = RestauMenuItems()
        override fun getPageTitle(position: Int): CharSequence? { return tabs.get(position) }
    }

}
