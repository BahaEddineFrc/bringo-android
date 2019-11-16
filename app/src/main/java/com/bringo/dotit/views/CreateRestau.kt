package com.bringo.dotit.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.bringo.dotit.R
import com.bringo.dotit.databinding.CreateRestauFragmentBinding
import com.bringo.dotit.models.User
import com.bringo.dotit.viewmodels.CreateRestauViewModel

class CreateRestau : Fragment() {

    companion object {
        fun newInstance() = CreateRestau()
    }

    private lateinit var viewModel: CreateRestauViewModel
    private lateinit var binding: CreateRestauFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.create_restau_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CreateRestauViewModel::class.java)
        binding.viewmodel=viewModel

        initViewModelListeners()
    }

    private fun initViewModelListeners() {
        viewModel.restauLiveData.observe(this, Observer{restau->
            var bundle = bundleOf("restauId" to restau._id)
            findNavController().navigate(R.id.action_createRestau_to_restauMenu,bundle)
        })
    }
}
