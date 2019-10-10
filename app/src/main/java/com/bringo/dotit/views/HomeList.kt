package com.bringo.dotit.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.OnCategoryClickListener
import com.bringo.dotit.R
import com.bringo.dotit.adapters.RestaurantsRVAdapter
import com.bringo.dotit.databinding.HomeListBinding
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.RestaurantViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home_list.*
import kotlinx.android.synthetic.main.fragment_home_list.view.*
import kotlinx.android.synthetic.main.fragment_home_list.view.home_logo


class HomeList : Fragment() {


    //private var listener: OnFragmentInteractionListener? = null
    var restauRecycler : RecyclerView?=null
    lateinit var progressBar: ProgressBar
    lateinit var floatingActionButton: FloatingActionButton

    private lateinit var mAdapter: RestaurantsRVAdapter
    private lateinit var restauViewModel: RestaurantViewModel
    private lateinit var binding : HomeListBinding
    var dataList: ArrayList<Restaurant> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            binding = inflate(inflater,R.layout.fragment_home_list, container,false)
            val view = binding.root

            restauRecycler=view.findViewById(R.id.restaurants_rv) as RecyclerView
            progressBar=view.findViewById(R.id.home_progressbar)
            floatingActionButton=view.findViewById(R.id.fab_home)


            //load viewModel
            restauViewModel = ViewModelProviders.of(this).get(RestaurantViewModel::class.java)

            initRecyclerView()

            view.home_logo.setOnClickListener { v:View ->
                v.findNavController().navigate(R.id.action_homeList_to_restauMenu)
            }

            return view
    }


    fun initRecyclerView(){

        mAdapter = RestaurantsRVAdapter{restau->
            //Hell("clicked restau : ${restau}")
            var bundle = bundleOf("restauId" to restau._id)
            findNavController().navigate(R.id.action_homeList_to_restauMenu,bundle)
        }

        val layoutManager = GridLayoutManager(context,2)
        //layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.restaurants_rv.layoutManager = layoutManager
        mAdapter.setRestauList(dataList) //delete
        binding.root.restaurants_rv.adapter = mAdapter

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {
        //listen to data changes in the ViewModel
        restauViewModel.getAllRestaurants()
        restauViewModel.arrayMutableLiveData.observe(this, Observer { restaurants->
            if(restaurants!=null) {
                mAdapter.setRestauList(restaurants)
            }
        })
    }

}
