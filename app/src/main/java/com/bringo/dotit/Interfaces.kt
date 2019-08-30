package com.bringo.dotit

import com.bringo.dotit.models.CategoryModel

interface OnCategoryClickListener {
    //fun onCategoryClick(position: Int)
    fun onCategoryClick(category: CategoryModel)
}