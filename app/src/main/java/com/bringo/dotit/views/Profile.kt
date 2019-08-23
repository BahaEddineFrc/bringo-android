package com.bringo.dotit.views


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
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


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Profile : Fragment() {

    lateinit var progressBar:ProgressBar
    lateinit var btnLogin:Button
    var loginScreenEnabled:Boolean =true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View=inflater.inflate(R.layout.fragment_profile, container, false)


        var registerLayout =view.findViewById<View>(R.id.register_fields)

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
        }


        val model = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        model.userLiveData?.observe(this, Observer { user->
            handleButtonFinishedLoading(spin_kit,btnLogin)
        })

        return view
    }


}
