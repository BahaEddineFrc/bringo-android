package com.bringo.dotit.viewmodels

import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestauMenuViewModel : ViewModel() {

    var imageUrl = ObservableField<String>()
    var name = ObservableField<String>()
    var rating = ObservableField<Float>()
    var description = ObservableField<String>()
    var restauId:String?=null
    var showDialog = MutableLiveData<Int>()

    fun createDish(v: View) {
        showDialog.value=1
    }

    var restaurantLiveData = MutableLiveData<Restaurant>()

    fun getRestauById(id:String?) {
        restauId=id
        if (id!=null)
        ApiFactory.retrofit.getRestauById(id).enqueue(object : Callback<Restaurant> {
            override fun onResponse(
                call: Call<Restaurant>,
                response: Response<Restaurant>
            ) {
                if (response.isSuccessful) {
                    restaurantLiveData.value = response.body()
                    name.set(response.body()!!.name)
                    imageUrl.set(response.body()!!.pic)
                    rating.set(response.body()!!.stars)
                    description.set(response.body()!!.address)

                }else{
                    Hell("getRestauById unSuccessful:" + response.code())
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Hell("getRestauById err:" + t.message!!)
            }
        })
    }


}