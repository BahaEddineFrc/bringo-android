package com.bringo.dotit.models

import androidx.room.Entity

@Entity
class CategoryModel {

     var  _id:String= ""
     var  name:String= ""
     var  waitTime:String= ""
     var  averagePrice:String= ""
     var  pic:String= ""
     var  nbr:String= ""//

     constructor(_id: String, name: String, time: String, averagePrice: String, pic: String, nbr: String) {
          this._id = _id
          this.name = name
          this.waitTime = time
          this.averagePrice = averagePrice
          this.pic = pic
          this.nbr = nbr
     }

     override fun toString(): String {
          return "CategoryModel(_id='$_id', name='$name', time='$waitTime', price='$averagePrice', pic='$pic', nbr='$nbr')"
     }


}