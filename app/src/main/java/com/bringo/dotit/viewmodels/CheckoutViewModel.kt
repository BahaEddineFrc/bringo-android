package com.bringo.dotit.viewmodels

import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CheckoutViewModel : ViewModel(){

    var dishName : String = ""
    var deliveryTime : String = ""
    var restauName : String = ""
    var fullname : String = ""
    var address : String = ""
    var phone : String = ""
    var price : String = ""

    init {
        loadDishForCheckout()
    }

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun loadDishForCheckout() {
        scope.launch {
            //userLiveData!!.postValue(repository.getConnectedUser().value)
        }
    }

}