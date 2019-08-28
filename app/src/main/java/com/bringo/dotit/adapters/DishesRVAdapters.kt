package com.bringo.dotit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.adapters.DishesRVAdapters.DishesViewHolder
import com.bringo.dotit.databinding.CategoryBinding
import com.bringo.dotit.databinding.RestaurantBinding
import com.bringo.dotit.models.DishModel

class DishesRVAdapters (val context: Context?)
    : RecyclerView.Adapter<DishesViewHolder>() {

    private val dishesArray: ArrayList<DishModel> =ArrayList()

    fun setDishesList(dishesArray: ArrayList<DishModel> ){
        dishesArray.clear()
        dishesArray.addAll(dishesArray)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val catBinding = DataBindingUtil.inflate(layoutInflater, R.layout.category_card, parent,false) as CategCardBinding
        return DishesViewHolder(catBinding)

    }

    override fun getItemCount(): Int {
        return dishesArray.size
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val dish = dishesArray[position]
        holder.bind(dish)
    }

    class DishesViewHolder(val catBinding: CategCardBinding):RecyclerView.ViewHolder(CategCardBinding.root){
        fun bind( dish: DishModel) {
            catBinding.restaurantmodel  = dish
            catBinding.executePendingBindings()

        }
    }

}
