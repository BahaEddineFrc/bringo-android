package com.bringo.dotit.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {


    private val repository : Repository = Repository(ApiFactory.retrofit)

    lateinit var email : MutableLiveData<String>
    lateinit var fullname : MutableLiveData<String>
    lateinit var address : MutableLiveData<String>
    lateinit var phone : MutableLiveData<String>
    lateinit var pic : MutableLiveData<String>
    var userLiveData : MutableLiveData<User>? = null

    fun getImageUrl():String {
        return pic.value as String
    }

    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun getProfileInfo() {
        scope.launch {
            //userLiveData!!.postValue(repository.getConnectedUserProfile().value)
        }
    }


}