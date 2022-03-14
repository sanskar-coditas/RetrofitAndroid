package com.example.retrofitandroid

import JokesDataRepo
import MainViewModel
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbdemo.db.JokesDatabase


class JokesAllActivity:AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerview:RecyclerView
    private lateinit var jokesArrayList: ArrayList<MyJokesData>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_jokes)


        val dao = JokesDatabase.getDatabase(applicationContext).jokesDoa()
        val repository = JokesDataRepo(dao)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        recyclerview = findViewById(R.id.allJokesList)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        jokesArrayList = arrayListOf()

       mainViewModel.getJokes().observe(this) {

            jokesArrayList = (it as ArrayList<MyJokesData>?)!!
            Log.i("Showing list", jokesArrayList.toString())
            val adapter = JokesAdapter(jokesArrayList)
            recyclerview.adapter = adapter
            Log.i("Show data final", it.toString())
        }


    }


}