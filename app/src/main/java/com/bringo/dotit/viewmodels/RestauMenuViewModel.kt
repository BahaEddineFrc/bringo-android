package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestauMenuViewModel : ViewModel() {

    private var tabs : List<String> = listOf("Breakfast", "Lunch", "Dinner")

    var img : String =""

    fun getImageUrl():String {
        return img
    }

    var restaurantLiveData = MutableLiveData<Restaurant>()

    fun getRestauById(id:String) {
        ApiFactory.retrofit.getRestauById(id).enqueue(object : Callback<Restaurant> {
            override fun onResponse(
                call: Call<Restaurant>,
                response: Response<Restaurant>
            ) {
                if (response.isSuccessful) {
                    //Hell("getRestauById :" + response.body())
                    restaurantLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Hell("getRestauById err:" + t.message!!)
            }
        })
    }


}