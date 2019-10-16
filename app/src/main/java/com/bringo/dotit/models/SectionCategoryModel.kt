package com.bringo.dotit.models

import androidx.room.Entity
import java.io.Serializable

@Entity
class SectionCategoryModel: Serializable {

    var  _id:String= ""
    var  cat:CategoryModel

    constructor(_id: String, cat: CategoryModel) {
        this._id = _id
        this.cat = cat
    }

    override fun toString(): String {
        return "SectionCategoryModel(_id='$_id', categories=$cat)"
    }

}