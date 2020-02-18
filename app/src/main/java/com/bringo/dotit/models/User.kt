package com.bringo.dotit.models

import android.util.Patterns
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bringo.dotit.utils.RESTAURANT_TYPE
import com.bringo.dotit.utils.USER_TYPE
import java.io.Serializable


@Entity
class User():Serializable{
    @PrimaryKey(autoGenerate = true)
    var  _id:String = ""
    var  fullname:String = ""
    var  email:String = ""
    var  password:String = ""
    var  address:String = ""
    var  phone:String = ""
    var  pic:String = ""
    var  type:String = ""
    var  ownedRestau:String? = null

    constructor(id: String, fullname: String, email: String, password: String, address: String, phone: String, pic: String, type:String, ownedRestau:String?) : this() {
        this._id = id
        this.fullname = fullname
        this.address = address
        this.email = email
        this.password = password
        this.phone = phone
        this.pic = pic
        this.type = type
        this.ownedRestau = ownedRestau
    }



    fun getImageUrl():String {
            return pic
        }


    fun isRestaurant(): Boolean {
        return RESTAURANT_TYPE===type
    }

    fun isCustomerUser(): Boolean {
        return USER_TYPE===type
    }

    fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordLengthGreaterThan5(): Boolean {
        return password.length > 5
    }

    override fun toString(): String {
        return "User(_id='$_id', fullname='$fullname', email='$email', password='$password', address='$address', phone='$phone', pic='$pic', type='$type')"
    }


}