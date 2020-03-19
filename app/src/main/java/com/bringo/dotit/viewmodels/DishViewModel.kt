package com.bringo.dotit.viewmodels

import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bringo.dotit.R
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.Order
import com.bringo.dotit.models.User
import com.bringo.dotit.repositories.Repository
import com.bringo.dotit.utils.Hell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishViewModel : ViewModel(){
    var dish=MutableLiveData<DishModel>()
    var sizes=ArrayList<String>()
    var prices=ArrayList<Double>()

    var dishName = ObservableField<String>()
    var dishDescription = ObservableField<String>()
    var dishNote = ObservableField<String>()
    var size = ObservableField<String>()
    var nbr = ObservableInt(1)
    var rate = ObservableInt()
    var totalPrice = ObservableFloat()
    var price = ObservableField<Double>()

    var pic : String = ""
    var sizeIndex : Int = 0

    fun intitializeDish(dish: DishModel) {

        for(tab in dish.sizes){
            sizes.add(tab.size)
            prices.add(tab.price)
        }
        dishName.set(dish.name)
        dishDescription.set(dish.description)
        //dishNote.set(order.note)
        //nbr.set(order.nbr)
        rate.set(dish.stars)
        price.set(prices[sizeIndex])
        size.set(sizes[sizeIndex])
        updateTotal()
        pic=dish.pic

    }

    fun getImageUrl():String {
        return pic
    }

    fun toNextSize() {
        if(sizeIndex < sizes.size-1) {
            sizeIndex++
            size.set(sizes[sizeIndex])
            price.set(prices[sizeIndex])
            updateTotal()
        }
    }

    fun toPrevSize() {
        if(sizeIndex > 0) {
            sizeIndex--
            size.set(sizes[sizeIndex])
            price.set(prices[sizeIndex])
            updateTotal()
        }
    }

    fun incNbr() {
        nbr.set(nbr.get().inc())
        updateTotal()
    }
    fun decNbr() {
        nbr.set(nbr.get().dec())
        updateTotal()
    }

    private fun updateTotal() {
        totalPrice.set(nbr.get().times(price.get()!!.toFloat()))
    }

    fun order(v: View) {
        dish.value?.let {

            val order=Order("",restauName,restauId,it._id, size.get()!!, nbr.get(),
                totalPrice.get(), 30, "being prepared")
            /*val bundle = bundleOf("dishName" to dishName.get(),
                "totalPrice" to totalPrice.get(),
                "dishDescription" to dishDescription.get())*/
            val bundle = bundleOf("order" to order)
            v.findNavController().navigate(R.id.action_selectedDish_to_dishCheckOut,bundle)
        }
       //Hell("ORDERING $dishName FOR ${totalPrice.get()} Dinars")

    }

    private val repository : Repository = Repository(ApiFactory.retrofit)
    private val scope = CoroutineScope(GlobalScope.coroutineContext)
    //used to execute functions in Async mode





}