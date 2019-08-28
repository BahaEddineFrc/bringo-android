package com.bringo.dotit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.models.CategoryModel

class CategoriesRVAdapter (val context: Context?) : RecyclerView.Adapter<CategoriesRVAdapter.CategoriesViewHolder>() {

    private val categoriesArray: ArrayList<CategoryModel> =ArrayList()


    fun setRestauList(categList: ArrayList<CategoryModel>) {
        categoriesArray.clear()
        categoriesArray.addAll(categList)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CategoriesRVAdapter.CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val catBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_card, parent,false) as CategCardBinding
        return CategoriesViewHolder(catBinding)
    }

    override fun getItemCount(): Int {
       return categoriesArray.size
    }

    override fun onBindViewHolder(holder: CategoriesRVAdapter.CategoriesViewHolder, position: Int) {
        val category = categoriesArray[position]
        holder.bind(category)
    }


    inner class CategoriesViewHolder (val categCardBinding:CategCardBinding):RecyclerView.ViewHolder(categCardBinding.root){
        fun bind( category: CategoryModel) {
            categCardBinding.restaurantmodel  = category
            categCardBinding.executePendingBindings()

        }
    }






}