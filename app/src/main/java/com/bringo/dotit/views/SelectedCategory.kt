package com.bringo.dotit.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.adapters.CategoriesRVAdapter
import com.bringo.dotit.adapters.DishesRVAdapters
import com.bringo.dotit.adapters.RestaurantsRVAdapter
import com.bringo.dotit.databinding.CategoryBinding
import com.bringo.dotit.databinding.HomeListBinding
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.CategoriesViewModel
import com.bringo.dotit.viewmodels.CategoryDishesViewModel
import com.bringo.dotit.viewmodels.DishViewModel
import com.bringo.dotit.viewmodels.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_home_list.view.*
import kotlinx.android.synthetic.main.fragment_selected_category.*
import kotlinx.android.synthetic.main.fragment_selected_category.view.*


class SelectedCategory : Fragment() {

    private lateinit var viewModel: CategoryDishesViewModel
    var restauRecycler : RecyclerView?=null

    private lateinit var mAdapter: DishesRVAdapters
    private lateinit var restauViewModel: CategoryDishesViewModel
    private lateinit var binding : CategoryBinding
    var dataList: ArrayList<DishModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view:View= inflater.inflate(R.layout.fragment_selected_category, container, false)

        //restauRecycler=view.findViewById(R.id.category_dishes_rv) as RecyclerView
        viewModel = ViewModelProviders.of(this).get(CategoryDishesViewModel::class.java)
        initRecyclerView()

        /*view.list_item.setOnClickListener { v:View ->
            v.findNavController().navigate(R.id.action_homeList_to_restauMenu)
        }*/

        return view
    }


    fun initRecyclerView(){

        mAdapter = DishesRVAdapters{dish->
            Hell("SelectedCateg: clicked dish: ${dish}")

            var bundle = bundleOf("dishId" to dish._id)
            findNavController().navigate(R.id.action_selectedCategory_to_selectedDish,bundle)
        }
        val layoutManager = LinearLayoutManager(context)
        //layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.category_dishes_rv.layoutManager = layoutManager
        mAdapter.setDishesList(dataList) //delete
        binding.root.category_dishes_rv.adapter = mAdapter

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {
        //listen to data changes in the ViewModel

        viewModel.dishesList.observe(this, Observer { dishes->
            Log.d("CategoryDishes","CategoryDishes subscribeDataCallBack ${dishes}")
            mAdapter.setDishesList(dishes)
        })

    }

}
