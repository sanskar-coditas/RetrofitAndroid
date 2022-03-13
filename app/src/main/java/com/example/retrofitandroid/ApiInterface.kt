package com.example.retrofitandroid

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.chucknorris.io/jokes/"
interface ApiInterface {

    // @GET("random")
    //fun getData(): Call<MyJokesData>
    @GET("random")
    suspend fun getData(): Response<MyJokesData>


    companion object {
        operator fun invoke(): ApiInterface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)
        }
    }


}