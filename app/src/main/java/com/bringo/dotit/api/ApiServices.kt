package com.bringo.dotit.api


import android.database.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.models.RestaurantsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Path


interface ApiServices {

    @GET("users")
    fun getUsersList( @Query("sources") newsSource: String,  @Query("apiKey") apiKey: String ): Call<Restaurant>

    @GET("/restaurants")
    fun getRestaus(): Observable<ArrayList<Restaurant>>

    @GET("restaurant/{id}")
    fun getRestaurantById(@Path("id") id:Int): Deferred<Response<Restaurant>>

    @GET("restaurant/popular")
    fun getPopularRestau(): Deferred<Response<RestaurantsResponse>>
}