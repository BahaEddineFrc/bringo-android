package com.bringo.dotit.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bringo.dotit.R
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.databinding.RestaurantBinding
import com.bringo.dotit.viewmodels.RestaurantViewModel

class RestaurantsRVAdapter(val context: Context?, val restaurantsArray: ArrayList<RestaurantViewModel>)
    : RecyclerView.Adapter<RestaurantsRVAdapter.RestaurantsViewHolder>() {


    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurantViewModel = restaurantsArray[position]
        holder.bind(restaurantViewModel)
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
        fun bind(restaurantViewModel: RestaurantViewModel) {
            restaurantBinding.restaurantmodel  = restaurantViewModel
            restaurantBinding.executePendingBindings()

        }
    }
}