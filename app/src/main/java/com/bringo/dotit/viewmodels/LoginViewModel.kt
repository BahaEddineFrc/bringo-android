package com.bringo.dotit.viewmodels

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {

    private val repository : Repository = Repository(ApiFactory.retrofit)

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var isRegisterScreenOn = MutableLiveData<Boolean>()
    var account = MutableLiveData<String>()

    var userLiveData: MutableLiveData<User>? = null

    init {
        isRegisterScreenOn.postValue(false)
        account.value="you don't have an account?"
        email.value="you don't have an"
    }


    fun getUser(): MutableLiveData<User> {
        if (userLiveData == null) {
            userLiveData = MutableLiveData<User>()
        }
        return userLiveData as MutableLiveData<User>
    }


    fun onClicked(v:View) {
        Toast.makeText(v.context,"toast played",Toast.LENGTH_LONG).show()
        Log.d("LoginViewModel","onClicked")

        v.findNavController().navigate(R.id.action_login_to_profile)

    }

    /*fun onLoginClicked(btn: Button, fullname: TextInputEditText, phone: TextInputEditText, address: TextInputEditText,
                       email: TextInputEditText, password: TextInputEditText, spin_kit: SpinKitView) {   */
    fun onLoginClicked(btnV: View, fullnameV: View, phoneV: View, addressV: View, emailV: View, passwordV: View) {
        //,fullname,phone,address, email,password,spin_kit
        Log.d("LoginViewModel","onLoginClicked")

        /*handleButtonLoading(spin_kit,btn)*/
        var btn  = btnV as Button
        var email  = emailV as TextInputEditText
        var password  = passwordV as TextInputEditText
        val user = User(email.text.toString(),password.text.toString())

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
            Login(user) //correct login -> change view
        }

    }


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun Login(user:User) {
        Log.d("LoginViewModel","Login")
        scope.launch {
            userLiveData!!.postValue(repository.getConnectedUser().value)
        }
    }



}