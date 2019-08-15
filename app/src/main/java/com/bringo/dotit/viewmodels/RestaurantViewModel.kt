package com.bringo.dotit.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.repositories.RestauRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext


class RestaurantViewModel : ViewModel{

    private val repository : RestauRepository = RestauRepository(ApiFactory.retrofit)

    var id: String=""
        var name: String=""
        var address: String=""
        var stars: Float=0f
        var pic: String=""

        constructor():super()
        constructor(restaurant: Restaurant) : super() {
            this.id = restaurant.id
            this.name = restaurant.name
            this.address = restaurant.address
            this.stars = restaurant.stars
            this.pic = restaurant.pic
        }

        fun getImageUrl():String {
            return pic
        }
        /********************************************************************/
        var arrayMutableLiveData = MutableLiveData<ArrayList<RestaurantViewModel>>()
        var array=ArrayList<RestaurantViewModel>()

        private val scope = CoroutineScope(coroutineContext) //used to execute functions in Async mode

    fun loadRestauList(): MutableLiveData<ArrayList<RestaurantViewModel>> {
            scope.launch {
                arrayMutableLiveData.postValue(repository.getRestausFromRepo().value)
            }
        return arrayMutableLiveData
        }

    fun cancelAllRequests() = coroutineContext.cancel()

    }

