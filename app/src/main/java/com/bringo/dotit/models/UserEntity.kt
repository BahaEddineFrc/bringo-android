package com.bringo.dotit.models

open class UserEntity {
    var id: String=""
    var name: String=""
    var address: String=""

    var email: String=""
    var password: String=""
    var phone: Int=0
    var pic: String=""

    constructor(id: String, name: String, address: String, email: String, password: String, phone: Int, pic: String) {
        this.id = id
        this.name = name
        this.address = address
        this.email = email
        this.password = password
        this.phone = phone
        this.pic = pic
    }
    constructor(id: String,name: String,address: String,pic: String) {
        this.id = id
        this.name = name
        this.address = address
        this.pic = pic
    }
}