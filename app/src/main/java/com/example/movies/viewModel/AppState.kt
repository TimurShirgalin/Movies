package com.example.movies.viewModel

import com.example.movies.model.CardData

sealed class AppState {
    data class Success(val data: List<CardData>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
