package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
data class Order(
    @PrimaryKey(autoGenerate = true)
    var _id: String="",
    var restauName: String,
    var restauId: String,
    var dishId: String,
    var size: String,
    var quantity: Int,
    var totalPrice: Float,
    var deliveryTime: Int,
    var deliveryStatus: String
     ) : Serializable {
    var fullname: String=""
    var address: String=""
    var phone: String=""
    var orderTime: Date?=null
}