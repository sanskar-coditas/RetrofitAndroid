package com.example.retrofitandroid

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "joke")
data class MyJokesData(

    @PrimaryKey(autoGenerate = true)
    val uid:Int,

    val created_at: String,
    val icon_url: String,
    val id: String,
    val updated_at: String,
    val url: String,
    val value: String
)