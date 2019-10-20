package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory.retrofit
import com.bringo.dotit.databinding.DishBinding
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.MenuModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.CategoryDishesViewModel
import com.bringo.dotit.viewmodels.DishViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectedDish : Fragment() {
    private lateinit var viewModel: DishViewModel
    private lateinit var binding: DishBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_selected_dish, container, false)
        viewModel = ViewModelProviders.of(this).get(DishViewModel::class.java)
        binding.dishviewmodel=viewModel

        initViewModelListeners()

        return binding.root
    }


    private fun initViewModelListeners() {
        val dish = arguments!!.getSerializable("dish") as DishModel
        viewModel.intitializeDish(dish)
        Hell("SelectedDish: received dish: ${dish}")
        if (dish==null) return
        //viewModel.getDishById(dishId)
    }


}
