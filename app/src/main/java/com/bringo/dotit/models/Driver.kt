package com.bringo.dotit.models

class Driver(id: String, name: String, address: String, email: String, password: String, phone: Int, pic: String) :
    UserEntity(id, name, address, email, password, phone, pic) {
}