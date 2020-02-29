package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.databinding.FragmentCreateDishBinding
import com.bringo.dotit.viewmodels.CreateDishViewModel
import com.bringo.dotit.viewmodels.ProfileViewModel

/**
 * A simple [Fragment] subclass.
 */
class CreateDish : Fragment() {
    lateinit var binding: FragmentCreateDishBinding
    lateinit var viewModel: CreateDishViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_dish, container, false)
        viewModel = ViewModelProviders.of(this).get(CreateDishViewModel::class.java)
        binding.viewmodel=viewModel

        initViewModelListeners()

        return binding.root
    }

    private fun initViewModelListeners() {
            viewModel.dishLiveData.observe(this, Observer {
                Toast.makeText(context!!,"the Dish has been successfully added!",Toast.LENGTH_LONG).show()
            })

    }


}
