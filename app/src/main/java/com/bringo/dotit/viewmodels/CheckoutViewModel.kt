package com.bringo.dotit.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.Order
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutViewModel : ViewModel(){

    var dishName = ObservableField<String>()
    var dishDescription = ObservableField<String>()
    var totalPrice = ObservableFloat()
    var deliveryTime = ObservableInt()
    var restauName = ObservableField<String>()

    var fullname : String = "" //share preferences
    var address : String = ""
    var phone : String = ""
    var dishToOrder:DishModel? = null

    fun checkout(v: View) {
        val bundle = bundleOf("dishName" to dishName.get())
        v.findNavController().navigate(R.id.action_dishCheckOut_to_deliveryTracking,bundle)
    }

    fun intitializeOrder(order: Order) {
        this.totalPrice.set(order.totalPrice)
        this.restauName.set(order.restauName)
        getDishById(order.dishId)
    }

    fun getDishById(id: String) {
        ApiFactory.retrofit.getDishById(id).enqueue(object : Callback<DishModel> {
            override fun onResponse(call: Call<DishModel>, response: Response<DishModel>) {
                if (response.isSuccessful) {
                    dishToOrder = response.body()
                    dishName.set(dishToOrder?.name)
                } else {
                    Hell("getRestauById unSuccessful ${response.code()}, msg:" + response.errorBody())
                }
            }

            override fun onFailure(call: Call<DishModel>, t: Throwable) {
                Hell("getRestauById err:" + t.message!!)
            }
        })
    }
}