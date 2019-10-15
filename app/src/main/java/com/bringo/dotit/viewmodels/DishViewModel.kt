package com.bringo.dotit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : ViewModel(){
    var dish=MutableLiveData<DishModel>()

    lateinit var dishName : MutableLiveData<String>
    var dishDescription : String = ""
    var dishNote : String = ""
    var nbr : Int = 1
    var rate : Int = 3
    var price : String = ""

    var pic : String = ""

    fun getImageUrl():String {
        return pic
    }


    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun getDishById(id: String) {
        ApiFactory.retrofit.getDishById(id).enqueue(object : Callback<DishModel> {
            override fun onResponse(call: Call<DishModel>, response: Response<DishModel>) {
                if (response.isSuccessful) {
                    dish.value = response.body()
                    dishName.value = (response.body() as DishModel).name
                } else {
                    Hell("getRestauById unSuccessful:" + response.code())
                }
            }

            override fun onFailure(call: Call<DishModel>, t: Throwable) {
                Hell("getRestauById err:" + t.message!!)
            }
        })
    }

}