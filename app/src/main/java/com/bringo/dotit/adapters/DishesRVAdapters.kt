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
import com.bringo.dotit.databinding.CategoryCardBinding
import com.bringo.dotit.databinding.DishCardBinding
import com.bringo.dotit.databinding.RestaurantBinding
import com.bringo.dotit.models.DishModel

class DishesRVAdapters (private val callback : (DishModel)->Unit) : RecyclerView.Adapter<DishesViewHolder>() {

    private val dishesArray: ArrayList<DishModel> =ArrayList()

    fun setDishesList(dishesArray: ArrayList<DishModel> ){
        dishesArray.clear()
        dishesArray.addAll(dishesArray)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dishBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dish_card, parent,false) as DishCardBinding
        return DishesViewHolder(dishBinding)

    }

    override fun getItemCount(): Int {
        return dishesArray.size
    }

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val dish = dishesArray[position]
        holder.bind(dish)
    }

    inner class DishesViewHolder(val dishBinding: DishCardBinding):RecyclerView.ViewHolder(dishBinding.root){
        fun bind( dish: DishModel) {
            dishBinding.cardLayout.setOnClickListener {
                callback.invoke(dish)
            }
            dishBinding.dishmodel  = dish
            dishBinding.executePendingBindings()

        }
    }


}
