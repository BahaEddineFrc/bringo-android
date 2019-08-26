package com.bringo.dotit.repositories

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.MutableLiveData
import com.bringo.dotit.api.ApiFactory
import com.bringo.dotit.models.Restaurant
import android.os.AsyncTask
import java.nio.file.Files.delete
import android.provider.ContactsContract.CommonDataKinds.Note
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.LiveData
import com.bringo.dotit.api.RestauDao
import android.app.Application
import android.util.Log
import com.bringo.dotit.api.ApiServices
import com.bringo.dotit.models.DishModel
import com.bringo.dotit.models.User
import com.bringo.dotit.viewmodels.RestaurantViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository (private val api : ApiServices) : BaseRepository() {



        suspend fun getRestausFromRepo(): MutableLiveData<ArrayList<Restaurant>> {//MutableList<Restaurant>?{

            var arrayMutableLiveData = MutableLiveData<ArrayList<Restaurant>>()
            var array=ArrayList<Restaurant>()

            var restaurant=Restaurant("id_1","Planet food","6 Mn",3.5f,"https://ksassets.timeincuk.net/wp/uploads/sites/55/2017/08/GettyImages-496903944-920x584.jpg")
            var restaurant2=Restaurant("id_2","El forno","1 Hr",3.5f, "https://ksassets.timeincuk.net/wp/uploads/sites/55/2017/08/GettyImages-496903944-920x584.jpg")

            //var homeViewModel= RestaurantViewModel(restaurant)
            //var homeViewModel2= RestaurantViewModel(restaurant2)

            array!!.add(restaurant)
            array!!.add(restaurant2)
            array!!.add(restaurant2)
            array!!.add(restaurant2)
            array!!.add(restaurant2)
            array!!.add(restaurant2)

            withContext(Dispatchers.Main){arrayMutableLiveData.value=array}

            return arrayMutableLiveData

            /*val restauResponse = safeApiCall(

                call = {api.getPopularRestau().await()},
                errorMessage = "Error Fetching Popular Restaurants"
            )

            return restauResponse?.results?.toMutableList()  */
        }

    fun getConnectedUser(): MutableLiveData<User> {
        var user= User("repository","test")
        lateinit var livedataUser:MutableLiveData<User>
        livedataUser.postValue(user)
        return livedataUser
    }

    suspend fun getConnectedUserProfile(): MutableLiveData<User> {
        var user= User("id_123","baha ferchichi","baha.ferchichi@aiesec.net","pass",
            "3835 green pond Rd, bethlehem, PA","+216 99 285 120","https://ksassets.timeincuk.net/wp/uploads/sites/55/2017/08/GettyImages-496903944-920x584.jpg")
        var livedataUser=MutableLiveData<User>()
        withContext(Dispatchers.Main){livedataUser!!.value=user}
        return livedataUser
    }

    fun getRestauDishes(): MutableLiveData<ArrayList<DishModel>> {
        var dish= DishModel("id_123","Pizza 4 seasons",12.5f,"Thon, tomato, olives",5,
            "https://ksassets.timeincuk.net/wp/uploads/sites/55/2017/08/GettyImages-496903944-920x584.jpg")
        var liveDishesList:MutableLiveData<ArrayList<DishModel>> = MutableLiveData<ArrayList<DishModel>>()
        var dishes=ArrayList<DishModel>() ; dishes.add(dish);dishes.add(dish);dishes.add(dish);dishes.add(dish);
        liveDishesList.postValue(dishes)
        return liveDishesList
    }


    /*
    private var newsRepository: Repository? = null
    lateinit var restauDao: RestauDao
    private lateinit var allRestaus: LiveData<List<Restaurant>>

    fun Repository(application: Application) {
        val database = NoteDatabase.getInstance(application)
        restauDao = database.noteDao()
        allRestaus = restauDao.getAllRestaurants()
    }

    private var retroApi: RetrofitApi? =null

    fun Repository() {
        retroApi = cteateService(RetrofitApi::class.java)
    }

    fun getNews(source: String, key: String): MutableLiveData<Restaurant> {
        val newsData = MutableLiveData<Restaurant>()
        retroApi!!.getRestauList(source, key).enqueue(object : Callback<Restaurant> {
            override fun onResponse(
                call: Call<Restaurant>,
                response: Response<Restaurant>
            ) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                newsData.setValue(null)
            }
        })
        return newsData
    }










    fun insert(restau: Restaurant) {
        InsertNoteAsyncTask(restauDao).execute(restau)
    }

    fun update(restau: Restaurant) {
        UpdateNoteAsyncTask(restauDao).execute(restau)
    }

    fun delete(restau: Restaurant) {
        DeleteNoteAsyncTask(restauDao).execute(restau)
    }

    fun deleteAllRestaus() {
        DeleteAllNotesAsyncTask(restauDao).execute()
    }

    fun getAllRestaus(): LiveData<List<Restaurant>> {
        return allRestaus
    }

    private class InsertNoteAsyncTask internal constructor(private val restauDao: RestauDao) :
        AsyncTask<Restaurant, Void, Void>() {

        override fun doInBackground(vararg restaus: Restaurant): Void? {
            restauDao.insert(restaus[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask internal constructor(private val restauDao: RestauDao) :
        AsyncTask<Restaurant, Void, Void>() {

        override fun doInBackground(vararg restaus: Restaurant): Void? {
            restauDao.update(restaus[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask internal constructor(private val restauDao: RestauDao) :
        AsyncTask<Restaurant, Void, Void>() {

        override fun doInBackground(vararg restaus: Restaurant): Void? {
            restauDao.delete(restaus[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask internal constructor(private val restauDao: RestauDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            restauDao.deleteAllRestaus()
            return null
        }
    }*/

}