package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Restaurant(    @PrimaryKey(autoGenerate = true)
                           var _id:String,
                          var  name:String,
                          var  email:String,
                          var  address:String,
                          var  phone:String,
                          var stars :Float, //
                          var avgTime :Int,
                          var menu :ArrayList<MenuModel>,
                          var  pic:String): Serializable

{
    fun getImageUrl():String {
        return pic
    }
 }
