package com.bringo.dotit.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Driver
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrackingViewModel : ViewModel(){

    var restauName : String = "restauName empty"
    var restauTime : String = "12:30"
    var timeProgress : Int = 25
    var dishName = ObservableField<String> ()


    lateinit var driverInfo : Driver

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode



    fun intitializeInfo(dishName: String?) {
        this.dishName.set(dishName)
    }

}