package com.bringo.dotit.models

import android.util.Patterns
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(var email: String="", var password: String=""){

    @PrimaryKey(autoGenerate = true)
    var  id:String = ""
    var  name:String = ""
    var  address:String = ""
    var  phone:String = ""
    var  pic:String = ""

    constructor(id: String, name: String, email: String, password: String, address: String, phone: String, pic: String) : this() {
        this.id = id
        this.name = name
        this.address = address
        this.email = email
        this.password = password
        this.phone = phone
        this.pic = pic
    }


    fun getImageUrl():String {
            return pic
        }




    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun isPasswordLengthGreaterThan5(): Boolean {
        return password.length > 5
    }
}