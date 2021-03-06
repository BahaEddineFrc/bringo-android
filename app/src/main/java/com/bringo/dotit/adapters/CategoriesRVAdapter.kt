package com.bringo.dotit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.OnCategoryClickListener
import com.bringo.dotit.R
import com.bringo.dotit.databinding.CategoryCardBinding
import com.bringo.dotit.models.CategoryModel
import android.R.attr.onClick
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import com.bringo.dotit.models.SectionCategoryModel


class CategoriesRVAdapter (private var callback :(CategoryModel)->Unit) : RecyclerView.Adapter<CategoriesRVAdapter.CategoriesViewHolder>(),
     View.OnClickListener{

    private val categoriesArray: ArrayList<SectionCategoryModel> =ArrayList()

    fun setRestauList(categList: ArrayList<SectionCategoryModel>) {
        categoriesArray.clear()
        categoriesArray.addAll(categList)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CategoriesRVAdapter.CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val catBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_card, parent,false) as CategoryCardBinding
        return CategoriesViewHolder(catBinding)
    }

    override fun getItemCount(): Int {
       return categoriesArray.size
    }

    override fun onBindViewHolder(holder: CategoriesRVAdapter.CategoriesViewHolder, position: Int) {
        val section = categoriesArray[position]
        holder.bind(section.cat)
    }

    override fun onClick(v: View) {
        Log.d("MenuCategories","onClicked item ")

    }


    inner class CategoriesViewHolder (var categCardBinding: CategoryCardBinding):RecyclerView.ViewHolder(categCardBinding.root){
        fun bind( category: CategoryModel) {

            //categCardBinding.setVariable(itemBinder.getBindingVariable(item), item);
            categCardBinding.cardLayout.setOnClickListener {
                callback.invoke(category)
            }
            categCardBinding.categorymodel  = category
            categCardBinding.executePendingBindings()

        }
    }






}

