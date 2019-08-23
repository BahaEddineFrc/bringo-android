package com.bringo.dotit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext


class RestaurantViewModel (application: Application) : AndroidViewModel(application){

    private val repository : Repository = Repository(ApiFactory.retrofit)


    var arrayMutableLiveData = MutableLiveData<ArrayList<Restaurant>>()

    private val scope = CoroutineScope(coroutineContext) //used to execute functions in Async mode

    fun loadRestauList(): MutableLiveData<ArrayList<Restaurant>> {
        scope.launch {
            arrayMutableLiveData.postValue(repository.getRestausFromRepo().value)
        }
        return arrayMutableLiveData
    }

    fun cancelAllRequests() = coroutineContext.cancel()

    }

