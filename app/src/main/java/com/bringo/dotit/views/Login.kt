package com.bringo.dotit.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.utils.handleButtonLoading
import com.bringo.dotit.viewmodels.LoginViewModel
import com.bringo.dotit.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.no_account_btn


class Login : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var btnLogin: Button
    var loginScreenEnabled:Boolean =true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_login, container, false)

        var btnLayout =view.findViewById<View>(R.id.btn_login_inc)
        var loginLayout =view.findViewById<View>(R.id.login_fields)
        var registerLayout =view.findViewById<View>(R.id.register_fields)

        btnLogin= btnLayout.findViewById<Button>(R.id.button_bottom_long)
        progressBar = btnLayout.findViewById(R.id.spin_kit) as ProgressBar


        view.no_account_btn.setOnClickListener {
            if(loginScreenEnabled){
                loginLayout.visibility=View.GONE
                registerLayout.visibility=View.VISIBLE
                no_account_btn.text = "you have an account?"
                btnLogin.text="Register"
                loginScreenEnabled=false
            }else{
                loginLayout.visibility=View.VISIBLE
                registerLayout.visibility=View.GONE
                no_account_btn.text = "you don't have an account?"
                btnLogin.text="Login"
                loginScreenEnabled=true
            }
        }
        btnLogin.setOnClickListener {
            handleButtonLoading(progressBar,btnLogin)
        }

        val model = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        return view
    }


}
