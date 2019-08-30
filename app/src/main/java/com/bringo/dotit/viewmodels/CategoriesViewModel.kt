package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.repositories.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {

    private val repository : Repository = Repository(ApiFactory.retrofit)

    var categoriesList : MutableLiveData<ArrayList<CategoryModel>> = MutableLiveData<ArrayList<CategoryModel>>()


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun onCategoryClick(){
        Log.d("CategoriesViewModel","CategoriesViewModel category clicked: ")

    }
    fun getCategories() {
        scope.launch {
            categoriesList!!.postValue(repository.getCategories().value)
        }.invokeOnCompletion {
            //Log.d("CategoriesViewModel",categoriesList.value.toString())
        }
    }



}
