package com.bringo.dotit.models

import androidx.room.Entity

@Entity
class CategoryModel {
     var  name:String= ""
     var  time:String= ""
     var  nbr:String= ""

     constructor(name: String, time: String, nbr: String) {
          this.name = name
          this.time = time
          this.nbr = nbr
     }
}