package com.bringo.dotit.api

    import androidx.room.*
    import com.bringo.dotit.models.Restaurant
    import io.reactivex.Flowable
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData



    @Dao
    interface RestauDao {

        @Query("SELECT * FROM RESTAURANT")
        fun getAll(): Flowable<List<Restaurant>>

        @Query("SELECT * FROM RESTAURANT WHERE id IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): Flowable<List<Restaurant>>

        @Query("SELECT * FROM USERS WHERE name LIKE :first AND "
                + "surname LIKE :last LIMIT 1")
        fun findByName(first:String, last:String): Flowable<Restaurant>


        @Query("SELECT * FROM RESTAURANT WHERE id = :userId")
        fun etById( userId: Int): Flowable<Restaurant>

        @Insert
        fun  insertAll(restaus: List<Restaurant>)

        @Update
        fun  updateAll(restaus: List<Restaurant>)

        @Insert
        fun  insert(restau: Restaurant)

        @Update
        fun  update(restau : Restaurant)

        @Delete
        fun  delete(restau : Restaurant)

        @Query("DELETE FROM RESTAURANT")
        fun  deleteAllRestaus()

        @Query("SELECT * FROM RESTAURANT")
        fun getAllRestaurants(): LiveData<List<Restaurant>>

    }