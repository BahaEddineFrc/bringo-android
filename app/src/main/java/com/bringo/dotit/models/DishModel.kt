package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DishModel() {
    var id : String= ""
    var  name:String= ""
    var  price:Float = 0f
    var  description:String= ""
    var stars :Int = 0
    var  pic:String= ""

    constructor(
        id: String,
        name: String,
        price: Float,
        description: String,
        stars: Int,
        pic: String
    ):this() {
        this.id = id
        this.name = name
        this.price = price
        this.description = description
        this.stars = stars
        this.pic = pic
    }

    fun getImageUrl():String {
        return pic
    }
}