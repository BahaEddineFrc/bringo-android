package com.bringo.dotit.models

import androidx.room.PrimaryKey

data class DishSizes(@PrimaryKey(autoGenerate = true)
                     var _id:String,
                     var  size:String,
                     var  price:Double) {
}