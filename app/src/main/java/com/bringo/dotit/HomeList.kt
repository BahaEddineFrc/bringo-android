package com.bringo.dotit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.adapters.RestaurantsRVAdapter
import com.bringo.dotit.viewmodels.RestaurantViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeList : Fragment() {
    //private var listener: OnFragmentInteractionListener? = null
    var restauRecycler : RecyclerView?=null
    lateinit var progressBar: ProgressBar
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var mAdapter: RestaurantsRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_home_list, container, false) as View

        restauRecycler=view.findViewById(R.id.restaurants_rv) as RecyclerView
        progressBar=view.findViewById(R.id.home_progressbar)
        floatingActionButton=view.findViewById(R.id.fab_home)

        initRecyclerView()

        return view
    }

    fun initRecyclerView(){
        //load viewModel
        var restaurantViewModel:RestaurantViewModel=
            ViewModelProviders.of(this).get(RestaurantViewModel::class.java)

        //load data from viewModel
        //restaurantViewModel.loadRestauList()

        //get and listen to changes in that data
        restaurantViewModel.loadRestauList().observe(this, Observer { restaurantViewModels->
            if(restaurantViewModels!=null) {
                Log.d("HomeList", "restaurantViewModels $restaurantViewModels")
                mAdapter = RestaurantsRVAdapter(context, restaurantViewModels!!)
                restauRecycler!!.layoutManager = LinearLayoutManager(context)
                restauRecycler!!.adapter = mAdapter
            }

        })


    }




}
