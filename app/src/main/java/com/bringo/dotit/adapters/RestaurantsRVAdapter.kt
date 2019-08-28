package com.bringo.dotit.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.OnCategoryClickListener
import com.bringo.dotit.R
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.databinding.RestaurantBinding

class RestaurantsRVAdapter(val context: Context?)
    : RecyclerView.Adapter<RestaurantsRVAdapter.RestaurantsViewHolder>() {
    private val restaurantsArray: ArrayList<Restaurant> =ArrayList()

    fun setRestauList(restauList: ArrayList<Restaurant>) {
        restaurantsArray.clear()
        restaurantsArray.addAll(restauList)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurantsArray[position]
        holder.bind(restaurant)
        //holder.bind(restaurantViewModel,listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val restaurantBinding = DataBindingUtil.inflate(layoutInflater, R.layout.home_restau_card, parent,false) as RestaurantBinding
        return RestaurantsViewHolder(restaurantBinding)
    }

    override fun getItemCount(): Int {
        Log.d("LIST_SIZE","" + restaurantsArray.size)
        return restaurantsArray.size
    }




    inner class RestaurantsViewHolder (val restaurantBinding:RestaurantBinding):RecyclerView.ViewHolder(restaurantBinding.root){
        fun bind( restaurant: Restaurant) {
            restaurantBinding.restaurantmodel  = restaurant

            restaurantBinding.executePendingBindings()

        }
    }
}