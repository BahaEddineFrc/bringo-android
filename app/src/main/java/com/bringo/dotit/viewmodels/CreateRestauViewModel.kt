package com.bringo.dotit.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.models.User
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRestauViewModel : ViewModel() {

    val email = ObservableField<String>()
    val restauName = ObservableField<String>()
    val address = ObservableField<String>()
    val phone = ObservableField<String>()
    val pic = ObservableField<String>()

    var restauLiveData = MutableLiveData<Restaurant>()

    fun getImageUrl(): String {
        return pic.get() as String
    }


    fun onRestauSave() {
        createNewRestaurant()
    }

    private fun createNewRestaurant() {
        ApiFactory.retrofit.createRestau(restauName.get().
            toString(),address.get().toString(),
            phone.get().toString(),email.get().toString(),pic.get().toString())
            .enqueue(object : Callback<Restaurant> {

            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>)
            {
                if(response.isSuccessful){
                    Hell("createRestau body :"+response.body())
                    restauLiveData.value=response.body()
                }else{
                    Hell("createRestau notSuccessful:"+response.code())
                }
            }
            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Hell("createRestau onFailure:"+t.message!!)
            }
        })
    }
}
