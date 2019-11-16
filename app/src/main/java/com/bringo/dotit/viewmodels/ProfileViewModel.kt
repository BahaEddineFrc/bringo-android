package com.bringo.dotit.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {


    val email = ObservableField<String>()
    val fullname = ObservableField<String>()
    val address = ObservableField<String>()
    val phone = ObservableField<String>()
    val pic = ObservableField<String>()
    val fabIsVisible = ObservableField<Int>()
    var type = String()

    var userLiveData = MutableLiveData<User>()

    fun getImageUrl():String {
        return pic.get() as String
    }


    fun getProfileInfo() {

    }

    fun myRestauClicked(v: View) {
        v.findNavController().navigate(R.id.action_profile_to_createRestau)
    }

    fun intitializeProfile(user: User) {
        email.set(user.email)
        fullname.set(user.fullname)
        address.set(user.address)
        phone.set(user.phone)
        pic.set(user.pic)
        type=user.type
        if (type=="restaurant") fabIsVisible.set(View.VISIBLE) else fabIsVisible.set(View.GONE)
    }


}