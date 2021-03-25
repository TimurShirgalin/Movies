package com.example.movies.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.movies.viewModel.AppState

class CardSourceImpl : CardSource {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getDataFromOutSource(liveDataToObserve: MutableLiveData<AppState>)/*: List<Categories>*/ {
        NewLoadMovieData().getGenresData("ru-RU", liveDataToObserve)
//        return loadData(liveDataToObserve)
    }

}
