package com.bringo.dotit.models

import androidx.room.PrimaryKey

data class MenuModel(@PrimaryKey(autoGenerate = true)
                     var _id:String,
                     var  sectionTitle:String,
                     var  sectionCategories:ArrayList<CategoryModel>) {
}


  //    "sectionCategories" : [ {"cat": "5d86d8596009992b417f553e"},
