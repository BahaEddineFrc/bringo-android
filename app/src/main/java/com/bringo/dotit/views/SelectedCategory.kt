package com.bringo.dotit.views


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.adapters.DishesRVAdapters
import com.bringo.dotit.databinding.CategoryBinding
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.CategoryDishesViewModel
import kotlinx.android.synthetic.main.fragment_selected_category.view.*


class SelectedCategory : Fragment() {

    private lateinit var viewModel: CategoryDishesViewModel
    var restauRecycler : RecyclerView?=null

    private lateinit var mAdapter: DishesRVAdapters
    private lateinit var binding : CategoryBinding
    var dataList: ArrayList<DishModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_category, container, false)
        val view = binding.root
        viewModel = ViewModelProviders.of(this).get(CategoryDishesViewModel::class.java)
        initRecyclerView()


        return view
    }


    fun initRecyclerView(){

        mAdapter = DishesRVAdapters{dish->
            Hell("SelectedCateg: clicked dish: ${dish._id}")

            var bundle = bundleOf("dishId" to dish._id)
            findNavController().navigate(R.id.action_selectedCategory_to_selectedDish,bundle)
        }
        val layoutManager = LinearLayoutManager(context)
        binding.root.category_dishes_rv.layoutManager = layoutManager
        mAdapter.setDishesList(dataList) //delete
        binding.root.category_dishes_rv.adapter = mAdapter

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {

        var restauId=arguments?.getString("restauId")
        var categoryId=arguments?.getString("categoryId")
        viewModel.getDishesByCategory(restauId,categoryId)

        viewModel.dishesList.observe(this, Observer { dishes->
            mAdapter.setDishesList(dishes)
        })

    }

}
