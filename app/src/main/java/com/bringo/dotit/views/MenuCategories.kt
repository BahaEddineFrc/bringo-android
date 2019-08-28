package com.bringo.dotit.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bringo.dotit.R
import com.bringo.dotit.adapters.CategoriesRVAdapter
import com.bringo.dotit.adapters.RestaurantsRVAdapter
import com.bringo.dotit.databinding.HomeListBinding
import com.bringo.dotit.databinding.RestauMenuBinding
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.viewmodels.CategoriesViewModel
import com.bringo.dotit.viewmodels.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_home_list.view.*
import kotlinx.android.synthetic.main.menu_categories_fragment.view.*


class MenuCategories : Fragment() {

    companion object {
        fun newInstance() = MenuCategories()
    }

    private lateinit var viewModel: CategoriesViewModel
    var restauRecycler : RecyclerView?=null

    private lateinit var mAdapter: CategoriesRVAdapter
    private lateinit var binding : RestauMenuBinding
    var dataList: ArrayList<CategoryModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view:View= inflater.inflate(R.layout.menu_categories_fragment, container, false)

        //restauRecycler=view.findViewById(R.id.categories_rv) as RecyclerView
        viewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        initRecyclerView()

        /*view.list_item.setOnClickListener { v:View ->
            v.findNavController().navigate(R.id.action_homeList_to_restauMenu)
        }*/

        return view
    }


    fun initRecyclerView(){

        mAdapter = CategoriesRVAdapter(context)
        val layoutManager = LinearLayoutManager(context)
        //layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.categories_rv.layoutManager = layoutManager
        mAdapter.setRestauList(dataList) //delete
        binding.root.categories_rv.adapter = mAdapter

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {
        //listen to data changes in the ViewModel
        viewModel.getCategries()
        viewModel.categoriesList.observe(this, Observer { categories->
            Log.d("MenuCategories","${categories}")
            mAdapter.setRestauList(dataList)

        })

    }

}
