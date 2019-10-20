package com.bringo.dotit.viewmodels

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController

import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    var userLiveData = MutableLiveData<User>()

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var fullname = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var isRegisterScreenOn = ObservableInt(8)
    var account = ObservableField<String>()
    var buttonText = ObservableField<String>()



    init {
        //isRegisterScreenOn.postValue(View.GONE)
        account.set("you don't have an account?")
        buttonText.set("Login")
        email.value="baha2@gmail.com"

        fullname.value="baha2"
        password.value="000000"
        phone.value="99285120"
        address.value="red pond rd PA"

    }



    fun getPasswordQuality(): String {
        return if (password.value.toString().isEmpty()) {
            "Enter a password"
        } else if (password.value.toString() == "password") {
            "Very bad"
        } else if (password.value.toString().length < 6) {
            "Short"
        } else {
            ""
        }
    }





    fun onLoginRegisterToggle() {
        if (isRegisterScreenOn.get()==View.GONE) {
            isRegisterScreenOn.set(View.VISIBLE)
            account.set("you have an account")
            buttonText.set("Register")
        }else{
            isRegisterScreenOn.set(View.GONE)
            account.set("you don't have an account?")
            buttonText.set("Login")
        }
    }


    fun onLoginClicked() {
        if (isRegisterScreenOn.get()==View.GONE)
            login(email.value.toString(),password.value.toString())
        else
            register(fullname.value.toString(),email.value.toString(),password.value.toString(),
            phone.value.toString(),address.value.toString())

        /*
        if (!user.isEmailValid()) {
            email.error = "Enter a Valid E-mail Address"
            email.requestFocus()
        }
        else if (TextUtils.isEmpty(user.password)) {
            password.error = "Enter a Password"
            password.requestFocus()
        }
        else if (!user.isPasswordLengthGreaterThan5()) {
            password.error = "Enter at least 6 Digit password"
            password.requestFocus()
        }
        else {
            login(user) //correct login -> change view
        }
        */

    }


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    private fun login(email: String, password: String) {
        ApiFactory.retrofit.signIn(email,password).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>)
            {
                if(response.isSuccessful){
                    Hell("Login -> signIn :"+response.body())
                    userLiveData.value=response.body()
                }else{
                    Hell("Login -> signIn notSuccessful:"+response.code())
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Hell("Login -> signIn err:"+t.message!!)
            }

        })
    }

    private fun register(fullname: String,email: String,password: String,phone: String,address: String) {
        val pic="https://library.kissclipart.com/20181005/bee/kissclipart-white-person-icon-png-clipart-computer-icons-deskt-73f851694f2ebca8.jpg"
        ApiFactory.retrofit.signUp(fullname,email,password,phone,pic,address)
            .enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>)
            {
                if(response.isSuccessful){
                    Hell("Login -> register :"+response.body())
                    userLiveData.value=response.body()
                }else{
                    Hell("Login -> register notSuccessful:"+response.code())
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Hell("Login -> register err:"+t.message!!)
            }
        })
    }



}