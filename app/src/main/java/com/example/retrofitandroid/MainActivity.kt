package com.example.retrofitandroid

import JokesDataRepo
import MainViewModel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbdemo.db.JokesDatabase

import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers.Main

import kotlinx.coroutines.launch

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
        val buttonForJokeList:Button=findViewById(R.id.allJokesList)
       val dao = JokesDatabase.getDatabase(applicationContext).jokesDoa()
        val repository = JokesDataRepo(dao)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)


        buttonForNextJokes.setOnClickListener {
                CoroutineScope(Main).launch {
                    getMyData()
                }
            }
        txtForData= findViewById(R.id.txtForData)

        buttonForJokeList.setOnClickListener {
        val intent= Intent(this,JokesAllActivity::class.java)
            startActivity(intent)
        }

    }

    private suspend fun getMyData() {

        val response = ApiInterface().getData()
        val responseBody = response.body()!!
        val myStringBuilder = StringBuilder()
        myStringBuilder.append(responseBody.value)
        myStringBuilder.append("\n")
        Log.d("MainActivity", "IDOFJOKE: $myStringBuilder")
        txtForData.text = myStringBuilder
        Log.e("MainActivity", "Database of joke: $responseBody")
        mainViewModel.insertJokes(responseBody)
        mainViewModel.getJokes().observe(this, Observer {

            Log.i("Show data final", it.toString())
        })


    }


}