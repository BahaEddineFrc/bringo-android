package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class DishModel (@PrimaryKey(autoGenerate = true)
                      var _id:String,
                      var  name:String,
                      var  description:String,
                      var  category:CategoryModel,
                      var  price:String,
                      var  stars:Int,
                      var  pic:String,
                      var  sizes:ArrayList<DishSizes>):Serializable {


    fun getImageUrl():String {
        return pic
    }
}