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
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutViewModel : ViewModel(){

    var dishName = ObservableField<String>()
    var totalPrice = ObservableFloat()
    var deliveryTime = ObservableInt()
    var restauName = ObservableField<String>()

    var fullname : String = "" //todo get share preferences
    var address : String = ""
    var phone : String = ""

    var order:Order? = null


    fun checkout(v: View) {
        if (order?.restau!=null) {
            val bundle = bundleOf("order" to order)
            v.findNavController().navigate(R.id.action_dishCheckOut_to_deliveryTracking, bundle)
        }
    }

    fun intitializeOrder(order: Order, restauId: String?) {
        this.order=order
        this.dishName.set(order.dish.name)
        this.totalPrice.set(order.totalPrice)
        this.deliveryTime.set(order.deliveryTime)
        getRestauById(restauId)

    }

    fun getRestauById(id:String?) {
        if (id!=null)
            ApiFactory.retrofit.getRestauById(id).enqueue(object : Callback<Restaurant> {
                override fun onResponse(
                    call: Call<Restaurant>,
                    response: Response<Restaurant>
                ) {
                    if (response.isSuccessful) {
                        order?.restau = response.body()
                        restauName.set((response.body() as Restaurant).name)
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