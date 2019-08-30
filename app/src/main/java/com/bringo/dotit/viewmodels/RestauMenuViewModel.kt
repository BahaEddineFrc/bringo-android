package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class RestauMenuViewModel : ViewModel() {

    private var tabs : List<String> = listOf("Breakfast", "Lunch", "Dinner")


    var img : String =""

    fun getImageUrl():String {
        return img
    }

    fun onClick(){
        Log.d("RestauMenuViewModel","RestauMenuViewModel clicked: ")

    }
}