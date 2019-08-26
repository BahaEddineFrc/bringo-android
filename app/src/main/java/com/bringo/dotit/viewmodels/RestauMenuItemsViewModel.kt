package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RestauMenuItemsViewModel : ViewModel() {

    private val repository : Repository = Repository(ApiFactory.retrofit)

    var dishesList : MutableLiveData<ArrayList<DishModel>> = MutableLiveData<ArrayList<DishModel>>()


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun getRestauDishes() {
        scope.launch {
            dishesList!!.postValue(repository.getRestauDishes().value)
            Log.d("getRestauDishes","dishesList")
        }
    }



}
