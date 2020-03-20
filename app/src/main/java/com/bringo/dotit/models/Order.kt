package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    var _id: String="",
    var restau: Restaurant?,
    var dish: DishModel,
    var size: String,
    var quantity: Int,
    var totalPrice: Float,
    var deliveryTime: Int,
    var deliveryStatus: String
     ) : Serializable {
    var address : AddressModel?= null
    var orderTime: Date?=null
}