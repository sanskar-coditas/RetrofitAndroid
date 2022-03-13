package com.example.retrofitandroid


import JokesDataRepo
import MainViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainViewModelFactory(private val jokesDataRepo: JokesDataRepo ):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(jokesDataRepo) as T
    }



}