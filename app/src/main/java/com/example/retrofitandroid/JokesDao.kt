package com.example.retrofitandroid

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao  // used to perform curd and other operations
interface JokesDao {
    @Insert
    suspend fun insertJoke(myJokesData: MyJokesData)

    @Update
    suspend fun updateJoke(myJokesData: MyJokesData)

    @Delete
    suspend fun deleteJoke(myJokesData: MyJokesData)

    @Query("SELECT * FROM joke")
    fun getJoke():LiveData<List<MyJokesData>>
}