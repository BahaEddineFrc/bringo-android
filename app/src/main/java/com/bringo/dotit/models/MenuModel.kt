package com.bringo.dotit.models

import androidx.room.PrimaryKey
import java.io.Serializable

data class MenuModel(@PrimaryKey(autoGenerate = true)
                     var _id:String,
                     var  sectionTitle:String,
                     var  sectionCategories:ArrayList<SectionCategoryModel>):Serializable {
}


  //    "sectionCategories" : [ {"cat": "5d86d8596009992b417f553e"},
