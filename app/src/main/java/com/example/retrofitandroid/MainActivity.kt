package com.example.retrofitandroid

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


const val BASE_URL = "https://api.chucknorris.io/jokes/"
class MainActivity : AppCompatActivity() {
    lateinit var txtForData :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            getMyData()
        txtForData= findViewById(R.id.txtForData)


    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<MyJokesData?> {
            override fun onResponse(call: Call<MyJokesData?>, response: Response<MyJokesData?>) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                    myStringBuilder.append(responseBody.value)
                    myStringBuilder.append("\n")
                Log.d("MainActivity", "IDOFJOKE: $myStringBuilder")
                txtForData.text =myStringBuilder
            }

            override fun onFailure(call: Call<MyJokesData?>, t: Throwable) {
                Log.d("MainActivity","onFailure: "+t.message)
            }
        })
    }
}