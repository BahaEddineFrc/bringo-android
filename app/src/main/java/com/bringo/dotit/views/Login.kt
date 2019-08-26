package com.bringo.dotit.views


import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.utils.handleButtonFinishedLoading
import com.bringo.dotit.utils.handleButtonLoading
import com.bringo.dotit.viewmodels.LoginViewModel
import com.bringo.dotit.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.util.*


class Login : Fragment() {

    lateinit var progressBar:ProgressBar
    lateinit var btnLogin:Button
    var loginScreenEnabled:Boolean =true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_login, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val model = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        //val view:View = binding.root

        /*var registerLayout =view.findViewById<View>(R.id.register_fields)

        view.no_account_btn.setOnClickListener {
            if(loginScreenEnabled){
                registerLayout.visibility=View.VISIBLE
                no_account_btn.text = "you have an account?"
                btnLogin.text="Register"
                loginScreenEnabled=false
            }else{
                registerLayout.visibility=View.GONE
                no_account_btn.text = "you don't have an account?"
                btnLogin.text="Login"
                loginScreenEnabled=true
            }
        }*/

        model.getUser()
        model.userLiveData?.observe(this, Observer { user->
            //handleButtonFinishedLoading(spin_kit,btnLogin)
        })

        view.button_bottom_long.setOnClickListener { v:View ->
            Log.d("Login","onClicked no_account_btn")
            v.findNavController().navigate(R.id.action_login_to_profile)
        }

        return view
    }
}
