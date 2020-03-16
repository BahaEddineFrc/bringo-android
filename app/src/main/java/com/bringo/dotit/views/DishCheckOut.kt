package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.databinding.CheckoutBinding
import com.bringo.dotit.viewmodels.CheckoutViewModel


class DishCheckOut : Fragment() {

    lateinit var binding : CheckoutBinding
    lateinit var viewModel:CheckoutViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dish_check_out, container, false)
        viewModel = ViewModelProviders.of(this).get(CheckoutViewModel::class.java)
        binding.checkoutviewmodel=viewModel

        initViewModelListeners()

        return binding.root

    }
    private fun initViewModelListeners() {
        //val user = arguments!!.getSerializable("user") as User
        val dishName=arguments!!.getString("dishName","error getting name")
        val totalPrice=arguments!!.getFloat("totalPrice")
        val dishDescription=arguments!!.getString("dishDescription","error getting descr")
        viewModel.intitializeOrder(dishName,totalPrice,dishDescription)
    }

}
