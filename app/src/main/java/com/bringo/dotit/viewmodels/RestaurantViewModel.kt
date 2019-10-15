package com.bringo.dotit.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.api.ApiFactory.retrofit
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.repositories.Repository
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantViewModel (application: Application) : AndroidViewModel(application) {

    var arrayMutableLiveData = MutableLiveData<ArrayList<Restaurant>>()

    fun getAllRestaurants() {
        retrofit.getAllRestaurants().enqueue(object : Callback<ArrayList<Restaurant>> {
            override fun onResponse(
                call: Call<ArrayList<Restaurant>>,
                response: Response<ArrayList<Restaurant>>
            ) {
                if (response.isSuccessful) {
                    arrayMutableLiveData.value = response.body()
                }else{
                    Hell("getAllRestaurants unSuccessful:" + response.code())
                }
            }

            override fun onFailure(call: Call<ArrayList<Restaurant>>, t: Throwable) {
                Hell("getAllRestaurants err:" + t.message!!)
            }
        })
    }




    private val scope = CoroutineScope(coroutineContext) //used to execute functions in Async mode

    /*fun getAllRestaurants(): MutableLiveData<ArrayList<Restaurant>> {
        scope.launch {
            try {
                val repository: Repository = Repository(ApiFactory.retrofit)

                arrayMutableLiveData.postValue(repository.getAllRestaurants().value)
            }catch (e : Exception){
                Hell("RestaurantViewModel :  " + e.message)
            }
        }
        return arrayMutableLiveData
    }*/



    fun cancelAllRequests() = coroutineContext.cancel()

    }

