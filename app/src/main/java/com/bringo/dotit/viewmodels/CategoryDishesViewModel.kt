package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryDishesViewModel : ViewModel(){

    var catName : String = ""
    var deliveryTime : String = ""
    var restauName : String = ""

    var dishesList : MutableLiveData<ArrayList<DishModel>> = MutableLiveData<ArrayList<DishModel>>()
    init {
        loadCategory()
    }

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun loadCategory() {
        scope.launch {
            //userLiveData!!.postValue(repository.getConnectedUser().value)
        }
    }

}