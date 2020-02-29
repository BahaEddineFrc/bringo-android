package com.bringo.dotit.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.CategoryModel
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class CategoryDishesViewModel : ViewModel(){

    var dishesList = MutableLiveData<ArrayList<DishModel>>()

    val catName = ObservableField<String>()
    val deliveryTime = ObservableField<String>()
    var restauName = ObservableField<String>()
    var isDishListEmpty = ObservableInt(0)
    var categoryId : String = ""
    var listSize : Int = 0


    private val scope = CoroutineScope(GlobalScope.coroutineContext) //used to execute functions in Async mode

    fun setUpCategory(category: CategoryModel, restauName: String?) {
        catName.set(category.name)
        deliveryTime.set(category.waitTime)
        this.restauName.set(restauName)
        categoryId=category._id
        Hell("setUpCategory name : ${category.name}")
    }

    fun createDish(v: View) {
        val bundle = bundleOf("catName" to catName, "categoryId" to categoryId)
        v.findNavController().navigate(R.id.action_selectedCategory_to_createDish,bundle)
    }

    fun isDishListEmpty(){
        if (listSize==0) isDishListEmpty.set(View.VISIBLE) else isDishListEmpty.set(View.GONE)
    }

    fun getDishesByCategory(restauId: String?) {
        if(restauId!=null && categoryId!=null)
        ApiFactory.retrofit.getDishesByCategory(restauId,categoryId).enqueue(object : Callback<ArrayList<DishModel>> {
            override fun onResponse(
                call: Call<ArrayList<DishModel>>, response: Response<ArrayList<DishModel>> ) {

                if (response.isSuccessful) {
                    //Hell("getDishesByCategory Successful ${response.body()!!.size} with ${restauId} and ${categoryId}" )
                    dishesList.value = response.body()
                    listSize=response.body()!!.size
                    isDishListEmpty()

                }else{
                    Hell("getDishesByCategory unSuccessful ${response.code()}, msg:" + response.errorBody())
                }
            }

            override fun onFailure(call: Call<ArrayList<DishModel>>, t: Throwable) {
                Hell("getDishesByCategory err:" + t.message!!)
            }
        })

    }



}