package com.example.movies.viewModel

import com.example.movies.model.Categories

sealed class AppState {
    data class Success(val data: List<Categories>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
