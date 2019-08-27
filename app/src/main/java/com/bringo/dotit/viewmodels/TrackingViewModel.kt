package com.bringo.dotit.viewmodels

import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Driver
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TrackingViewModel : ViewModel(){

    var restauName : String = ""
    var restauTime : String = ""
    var timeZone : String = ""
    var timeProgress : Int = 25

    lateinit var driverInfo : Driver

    init {
        loadTrackingInfo()
    }

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun loadTrackingInfo() {
        scope.launch {
            //userLiveData!!.postValue(repository.getConnectedUser().value)
        }
    }

}