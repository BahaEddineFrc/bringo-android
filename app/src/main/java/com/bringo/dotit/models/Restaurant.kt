package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(    @PrimaryKey(autoGenerate = true)
                           var id:String,
                          var  name:String,
                          var  address:String,
                          var stars :Float,
                          var  pic:String){

 }

data class RestaurantsResponse(
    val results: List<Restaurant>
)