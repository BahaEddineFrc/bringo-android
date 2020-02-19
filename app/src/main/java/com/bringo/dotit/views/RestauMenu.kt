package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.bringo.dotit.R
import com.bringo.dotit.databinding.FragmentRestauMenuBinding
import com.bringo.dotit.models.MenuModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.RestauMenuViewModel
import com.bringo.dotit.viewmodels.RestaurantViewModel
import com.google.android.material.tabs.TabLayout
import java.io.Serializable


class RestauMenu : Fragment(){

    private lateinit var restauMenuViewModel: RestauMenuViewModel
    lateinit var binding : FragmentRestauMenuBinding
    private lateinit var mPager: ViewPager
    //private var tabs : List<String> = listOf("Breakfast", "Lunch", "Dinner")

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restau_menu, container, false)

         //load viewModel
         restauMenuViewModel = ViewModelProviders.of(this).get(RestauMenuViewModel::class.java)
         binding.restaumenuviewmodel=restauMenuViewModel

         mPager = binding.root.findViewById(R.id.pager)  // Instantiate a ViewPager and a PagerAdapter.


         val tabLayout : TabLayout = binding.root.findViewById(R.id.tab_layout)
         tabLayout.setupWithViewPager(mPager)

         subscribeDataCallback()

         return binding.root

    }

    private fun subscribeDataCallback() {
        var restauId: String? = arguments?.getString("restauId")
        Hell("clicked restauId : ${restauId}")

        restauMenuViewModel.getRestauById(restauId)
        restauMenuViewModel.restaurantLiveData.observe(this, Observer { restaurant->
            if(restaurant!=null) {

                Hell("restaurantLiveData : $restaurant")
                val pagerAdapter = ScreenSlidePagerAdapter(fragmentManager as FragmentManager, restaurant)
                // The pager adapter, which provides the pages to the view pager widget.
                mPager.adapter = pagerAdapter
            }
        })

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
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager, var restau:Restaurant) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = restau.menu.size
        override fun getItem(position: Int) : Fragment{
            val fragment = MenuCategories()
            val bundle = Bundle()
            bundle.putString("restauId", restau._id)
            bundle.putString("restauName", restau.name)
            bundle.putSerializable("menuPerTitle", restau.menu[position] as Serializable)
            fragment.arguments = bundle
            return fragment
        }
        override fun getPageTitle(position: Int): CharSequence? { return restau.menu[position].sectionTitle }
    }

}
