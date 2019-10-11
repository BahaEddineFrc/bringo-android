package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    class DishModel() {
    var _id : String= ""
    var  name:String= ""
    var  description:String= ""
    lateinit var  category:CategoryModel
    var  price:Float = 0f
    var stars :Int = 0 //
    var  pic:String= ""
    lateinit var sizes:ArrayList<DishSizes>



    fun getImageUrl():String {
        return pic
    }
}