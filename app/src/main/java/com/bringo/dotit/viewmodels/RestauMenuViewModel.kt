package com.bringo.dotit.viewmodels

import androidx.lifecycle.ViewModel

class RestauMenuViewModel : ViewModel() {

    private var tabs : List<String> = listOf("Breakfast", "Lunch", "Dinner")


    var img : String =""

    fun getImageUrl():String {
        return img
    }


}