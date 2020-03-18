package com.bringo.dotit.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckoutViewModel : ViewModel(){

    var dishName = ObservableField<String>()
    var dishDescription = ObservableField<String>()
    var totalPrice = ObservableFloat()
    var deliveryTime : String = "30m"
    var restauName : String = "Restaurant X"

    var fullname : String = ""
    var address : String = ""
    var phone : String = ""

    fun checkout(v: View) {
        val bundle = bundleOf("dishName" to dishName.get())
        v.findNavController().navigate(R.id.action_dishCheckOut_to_deliveryTracking,bundle)
        //Hell("ORDERING $dishName FOR ${totalPrice.get()} Dinars")
    }

    fun intitializeOrder(dishName: String, totalPrice: Float, dishDescription: String) {
        this.dishName.set(dishName)
        this.totalPrice.set(totalPrice)
    }
}