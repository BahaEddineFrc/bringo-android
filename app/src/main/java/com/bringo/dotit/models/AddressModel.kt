package com.bringo.dotit.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity
data class AddressModel(
    var fullname: String,
    var address: String,
    var phone: String
) : Serializable {
}