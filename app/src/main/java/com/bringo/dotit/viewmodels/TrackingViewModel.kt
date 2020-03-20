package com.bringo.dotit.viewmodels

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Driver
import com.bringo.dotit.models.Order
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class TrackingViewModel : ViewModel(){

    val deliveryTime : String
            get() = SimpleDateFormat("HH:mm", Locale.getDefault()).format( Date())

    var timePourcentage = ObservableInt()

    var timeRemaining = ObservableInt()

    var dishName = ObservableField<String> ()

    lateinit var driverInfo : Driver

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode



    fun intitializeInfo(order: Order) {
        this.dishName.set(order.dish.name)
        timePourcentage.set(30)
        timeRemaining.set(order.deliveryTime)
    }

}