package com.example.retrofitandroid

import JokesDataRepo
import MainViewModel
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbdemo.db.JokesDatabase
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder



class MainActivity : AppCompatActivity() {
    lateinit var txtForData :TextView
    lateinit var database: JokesDatabase
    lateinit var mainViewModel: MainViewModel
    private lateinit var jokesArrayList: LiveData<List<MyJokesData>>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonForNextJokes : Button= findViewById(R.id.nextButton)

       val DAO = JokesDatabase.getDatabase(applicationContext).jokesDoa()
        val repository = JokesDataRepo(DAO)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)





        buttonForNextJokes.setOnClickListener {
                CoroutineScope(Main).launch {
                    getMyData()
                }
            }
        txtForData= findViewById(R.id.txtForData)

    }

     private suspend fun getMyData() {

        val response= ApiInterface().getData()
        val responseBody = response.body()!!
        val myStringBuilder = StringBuilder()
        myStringBuilder.append(responseBody.value)
        myStringBuilder.append("\n")
        Log.d("MainActivity", "IDOFJOKE: $myStringBuilder")
        txtForData.text =myStringBuilder


        Log.e("MainActivity", "Database of joke: $responseBody")
            mainViewModel.insertJokes(responseBody)



         mainViewModel.getJokes().observe(this, Observer {

             Log.i("Show data final",  it.toString())
         })



        }

}