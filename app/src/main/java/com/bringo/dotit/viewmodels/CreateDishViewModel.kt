package com.bringo.dotit.viewmodels

import android.R
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.utils.Hell


class CreateDishViewModel(application: Application) : AndroidViewModel(application) {

    val name = ObservableField<String>()
    val description = ObservableField<String>()
    val category = ObservableField<String>()
    val pic = ObservableField<String>()

    val size = ObservableField<String>()
    val price = ObservableField<String>()

    var dishLiveData = MutableLiveData<Restaurant>()

    fun getImageUrl(): String {
        return pic.get() as String
    }


    fun addPriceLayer(v: View) {
        val pricesLayout = v.parent.parent as LinearLayout
        val newBlock: View = LayoutInflater
            .from(v.context).inflate(com.bringo.dotit.R.layout.dish_price_btn, null)
        Hell("parent: "+pricesLayout)
        //val newBlock = Button(v.context)
        pricesLayout.addView(newBlock)
    }

    fun deletePriceLayer(v: View) {
        var pricesLayout = v.parent as RelativeLayout
        Hell("parent: "+pricesLayout)

        //val newBlock = Button(v.context)
        pricesLayout=RelativeLayout(v.context)
    }
    fun createNewDish() {
        //todo add method
        dishLiveData.value= Restaurant("er","name","mail",
            "add","phone",3.0f,ArrayList(),"pic")
        /*ApiFactory.retrofit.createRestau(restauName.get().
            toString(),address.get().toString(),
            phone.get().toString(),email.get().toString(),pic.get().toString())
            .enqueue(object : Callback<Restaurant> {

                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>)
                {
                    if(response.isSuccessful){
                        Hell("createRestau body :"+response.body())
                        restauLiveData.value=response.body()
                    }else{
                        Hell("createRestau notSuccessful:"+response.code())
                    }
                }
                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    Hell("createRestau onFailure:"+t.message!!)
                }
            })*/
    }
}
