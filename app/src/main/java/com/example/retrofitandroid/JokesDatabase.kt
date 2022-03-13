package com.example.roomdbdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitandroid.JokesDao
import com.example.retrofitandroid.MyJokesData


@Database(entities = [MyJokesData::class], version = 1)
abstract class JokesDatabase : RoomDatabase(){

    //this approch is thread safe (singleton approach)
    abstract fun jokesDoa(): JokesDao

    companion object{

        @Volatile   //whenever new value assigns to instance variable; this annotation  will notify all threads with the updated value
        private var INSTANCE : JokesDatabase?=null

        fun getDatabase(context:Context): JokesDatabase {
            if(INSTANCE ==null)
            {
                synchronized(this)   //if multiple thread locking mechanism to create only one instance even if two thread access it
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,      //database instance
                        JokesDatabase::class.java,
                        "JokesDB").build()
                }
            }
            return INSTANCE!!
        }
    }





}