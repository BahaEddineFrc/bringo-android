package com.bringo.dotit.views


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.databinding.ProfileBinding
import com.bringo.dotit.models.User
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.utils.handleButtonFinishedLoading
import com.bringo.dotit.utils.handleButtonLoading
import com.bringo.dotit.viewmodels.LoginViewModel
import com.bringo.dotit.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*


class Profile : Fragment() {

    lateinit var binding : ProfileBinding
    lateinit var viewModel:ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding.profileviewmodel=viewModel

        initViewModelListeners()

        return binding.root
    }

    private fun initViewModelListeners() {
        val user = arguments!!.getSerializable("user") as User
        viewModel.intitializeProfile(user)
        viewModel.getProfileInfo()
    }

}
