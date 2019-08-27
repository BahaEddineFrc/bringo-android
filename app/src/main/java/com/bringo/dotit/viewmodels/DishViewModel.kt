package com.bringo.dotit.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DishViewModel : ViewModel(){

    var dishName : String = ""
    var dishDescription : String = ""
    var dishNote : String = ""
    var nbr : String = ""
    var rate : Int = 3
    var price : String = ""

    //lateinit var pic : MutableLiveData<String>
    var pic : String = ""

    fun getImageUrl():String {
        return pic
    }

    init {
        loadDishInfo()
    }

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun loadDishInfo() {
        scope.launch {
            //userLiveData!!.postValue(repository.getConnectedUser().value)
        }
    }

}