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
import com.bringo.dotit.utils.handleButtonFinishedLoading
import com.bringo.dotit.utils.handleButtonLoading
import com.bringo.dotit.viewmodels.LoginViewModel
import com.bringo.dotit.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*


class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_profile, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val model = ViewModelProviders.of(this).get(ProfileViewModel::class.java)


        model.getProfileInfo()
        model.userLiveData?.observe(this, Observer { user->
            //pic
            if (user!=null) {
                fullname.text = user.name
                email.text = user.email
                phone.text = user.phone
                address.text = user.address
            }
        })

        return view
    }


}
