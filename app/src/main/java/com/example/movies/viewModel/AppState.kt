package com.example.movies.viewModel

import com.example.movies.model.CardSource

sealed class AppState {
    data class Success(val data: CardSource) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
