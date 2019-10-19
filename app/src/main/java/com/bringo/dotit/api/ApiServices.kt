package com.bringo.dotit.api


import android.database.Observable
import com.bringo.dotit.models.DishModel
import retrofit2.Call
import com.bringo.dotit.models.Restaurant
import com.bringo.dotit.models.RestaurantsResponse
import com.bringo.dotit.models.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT
import retrofit2.http.GET












interface ApiServices {
//restaus 5d86d8c2956ab02c8ce2a976 5d86d8e2956ab02c8ce2a984
//categories 5d86d8596009992b417f553e 5da4fc52b7ba832cb091f83e
    /************************* AUTHENTIFICATION ******************/

    @FormUrlEncoded
    @PUT("/signin")
    fun signIn(
        @Field("email") email: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("/signup")
    fun signUp(
        @Field("fullname") fullname: String, @Field("email") email: String,
        @Field("password") password: String, @Field("phone") phone: String,
        @Field("pic") pic: String, @Field("address") address: String ): Call<User>

    @FormUrlEncoded
    @PUT("/changePassword")
    fun changePassword(@Field("oldPassword") oldPassword: String, @Field("newPassword") newPassword: String): Call<HashMap<String,String>>


    @PUT("/signout")
    fun signout(): Call<HashMap<String,String>>

    /************************* USERS ******************/


    @GET("/users")
    fun getAllUsers(): Call<HashMap<String,String>>

    @GET("/user/{id}")
    fun getUserById(@Path("id") type: String): Call<HashMap<String,String>>

    @DELETE("/user/{id}")
    fun deleteUser(@Path("id") type: String): Call<HashMap<String,String>>

    @FormUrlEncoded
    @PUT("/user/profile")
    fun updateProfile(
        @Field("fullname") fullname: String, @Field("email") email: String,
        @Field("password") password: String, @Field("phone") phone: String,
        @Field("pic") pic: String, @Field("address") address: String ): Call<HashMap<String,User>>


    /************************* RESTAURANTS ******************/


    @GET("/restaurants")
    fun getAllRestaurants(): Call<ArrayList<Restaurant>>

    @GET("/restaurant/{id}")
    fun getRestauById(@Path("id") type: String): Call<Restaurant>

    @FormUrlEncoded //todo finish this
    @POST("/restaurant")
    fun createRestau(
        @Field("name") fullname: String, @Field("email") email: String ): Call<HashMap<String,User>>

    @DELETE("/restaurant/{id}")
    fun deleteRestau(@Path("id") type: String): Call<HashMap<String,String>>


    /************************* CATEGORIES ******************/

    @GET("/categories")
    fun getAllCategories(): Call<HashMap<String,String>>

    @GET("/category/{id}")
    fun getCategoryById(@Path("id") type: String): Call<HashMap<String,String>>

    @FormUrlEncoded //todo finish this
    @POST("/category")
    fun createCategory(
        @Field("name") fullname: String, @Field("email") email: String ): Call<HashMap<String,User>>

    @DELETE("/category/{id}")
    fun deleteCategory(@Path("id") type: String): Call<HashMap<String,String>>


    /************************* DISHES ******************/

    @GET("/dishs")
    fun getAllDishes(): Call<HashMap<String,String>>

    @GET("/dish/{id}")
    fun getDishById(@Path("id") type: String): Call<DishModel>

    @GET("/dishes/{restauId}/{categoryId}")
    fun getDishesByCategory(@Path("restauId") restauId: String,@Path("categoryId") categoryId: String): Call<ArrayList<DishModel>>

    @FormUrlEncoded //todo finish this
    @POST("/dish")
    fun createDish(
        @Field("name") fullname: String, @Field("email") email: String ): Call<HashMap<String,User>>

    @DELETE("/dish/{id}")
    fun deleteDish(@Path("id") type: String): Call<HashMap<String,String>>







    /************************* EXTRA ******************/

    @GET("/users")
    fun getUsersList( @Query("sources") newsSource: String,  @Query("apiKey") apiKey: String ): Call<Restaurant>

    @GET("/restaurants")
    fun getRestaus(): Call<ArrayList<Restaurant>>

    @GET("/restaurant/popular")
    fun getPopularRestau(): Deferred<Response<RestaurantsResponse>>


    @GET("restaurant/{id}")
    fun getRestaurantById(@Path("id") id:Int): Deferred<Response<Restaurant>>


/*
    @GET("/user/{id}")
    fun getRestaurantById(@Path("id") id:Int): Deferred<Response<Restaurant>>

    @GET("/category/{id}")
    fun getRestaurantById(@Path("id") id:Int): Deferred<Response<Restaurant>>

    @GET("/dish/{id}")
    fun getRestaurantById(@Path("id") id:Int): Deferred<Response<Restaurant>>
*/


}
