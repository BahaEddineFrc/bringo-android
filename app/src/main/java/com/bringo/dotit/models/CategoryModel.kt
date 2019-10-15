package com.bringo.dotit.models

import androidx.room.Entity

@Entity
class CategoryModel {

     var  _id:String= ""
     var  name:String= ""
     var  time:String= ""
     var  price:String= ""
     var  pic:String= ""
     var  nbr:String= ""//

     constructor(_id: String, name: String, time: String, price: String, pic: String, nbr: String) {
          this._id = _id
          this.name = name
          this.time = time
          this.price = price
          this.pic = pic
          this.nbr = nbr
     }

     override fun toString(): String {
          return "CategoryModel(_id='$_id', name='$name', time='$time', price='$price', pic='$pic', nbr='$nbr')"
     }


}