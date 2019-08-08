package com.bringo.dotit.viewmodels

import com.bringo.dotit.models.User
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



    class HomeViewModel : ViewModel() {

        private val user: MutableLiveData<User>? = null

        fun getUser(): LiveData<User>? {
            return user
        }
    }