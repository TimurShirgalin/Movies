package com.example.movies.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.CardSourceImpl
import com.example.movies.model.Categories

@RequiresApi(Build.VERSION_CODES.N)
class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    init {
        initData()
    }

    private fun initData() {
        liveDataToObserve.value = AppState.Loading
        CardSourceImpl().getDataFromOutSource(liveDataToObserve)
    }

    fun postLiveData(categoriesData: MutableList<Categories>) =
        liveDataToObserve.postValue(AppState.Success(categoriesData))

    fun getMoviesData() = initData()

    fun getLiveData() = liveDataToObserve

}