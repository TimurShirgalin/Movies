package com.example.movies.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.model.CardSourceImpl

@RequiresApi(Build.VERSION_CODES.N)
class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {

    init {
        liveDataToObserve.value = AppState.Loading
        CardSourceImpl().getDataFromOutSource(liveDataToObserve)
    }

    fun getLiveData() = liveDataToObserve

}