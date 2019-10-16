package com.bringo.dotit.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.repositories.Repository
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDishesViewModel : ViewModel(){

    var catName : String = "namee"
    var catNameLV : MutableLiveData<String> =MutableLiveData<String>()
    var deliveryTime : String = ""
    var restauName : String = ""
    var categoryId : String = ""
    var listSize : Int = 0

    var dishesList : MutableLiveData<ArrayList<DishModel>> = MutableLiveData<ArrayList<DishModel>>()


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun setUpCategory(category: CategoryModel) {
        catName=category.name
        deliveryTime=category.waitTime
        categoryId=category._id
    }

    fun isDishListEmpty(): Int{
        return if (listSize==0) View.VISIBLE else View.GONE
    }

    fun getDishesByCategory(restauId: String?) {
        if(restauId!=null && categoryId!=null)
        ApiFactory.retrofit.getDishesByCategory(restauId,categoryId).enqueue(object : Callback<ArrayList<DishModel>> {
            override fun onResponse(
                call: Call<ArrayList<DishModel>>, response: Response<ArrayList<DishModel>> ) {
                if (response.isSuccessful) {
                    dishesList.value = response.body()
                    listSize=response.body()!!.size
                    Hell("getDishesByCategory Successful ${response.body()} with ${restauId} and ${categoryId}" )

                }else{
                    Hell("getDishesByCategory unSuccessful ${response.code()}, msg:" + response.errorBody())
                }
            }

            override fun onFailure(call: Call<ArrayList<DishModel>>, t: Throwable) {
                Hell("getDishesByCategory err:" + t.message!!)
            }
        })

    }



}