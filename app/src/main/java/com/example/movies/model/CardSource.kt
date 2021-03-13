package com.example.movies.model

import androidx.lifecycle.MutableLiveData
import com.example.movies.viewModel.AppState

interface CardSource {
    fun getDataFromOutSource(liveDataToObserve: MutableLiveData<AppState>): List<Categories>
}