package com.bringo.dotit.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.lifecycle.Observer

import com.bringo.dotit.R
import com.bringo.dotit.viewmodels.RestauMenuItemsViewModel



class RestauMenuItems : Fragment() {

    companion object {
        fun newInstance() = RestauMenuItems()
    }

    private lateinit var viewModel: RestauMenuItemsViewModel

    lateinit var list: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v:View= inflater.inflate(R.layout.restau_menu_items_fragment, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RestauMenuItemsViewModel::class.java)

        //list.adapter=
        viewModel.getRestauDishes()
        viewModel.dishesList.observe(this, Observer { dishes->
            Log.d("RestauMenuItems","${dishes}")


        })
    }

}
