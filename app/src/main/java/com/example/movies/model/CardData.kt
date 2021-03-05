package com.example.movies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardData(val category: String, val movieData: List<MovieData>) : Parcelable

fun getCardData(): List<CardData> {
    val cardData: MutableList<CardData> = mutableListOf()
    val movieCategories: List<String> = getMovieCategories()

    movieCategories.indices.forEach { i ->
        cardData.add(CardData(movieCategories[i], getMovieDataCard(movieCategories[i])))
    }
    return cardData
}

private fun getMovieDataCard(category: String): List<MovieData> {
    val movies: MutableList<MovieData> = mutableListOf()
    val movieData: List<MovieData> = getMovieData()

    movieData.indices.forEach { i ->
        if (movieData[i].category == category) {
            movies.add(movieData[i])
        }
    }
    return movies
}

private fun getMovieCategories(): List<String> {
    val movieCategories: MutableList<String> = mutableListOf()

    getMovieData().indices.forEach { i ->
        if (!movieCategories.contains(getMovieData()[i].category)) {
            movieCategories.add(getMovieData()[i].category)
        }
    }
    return movieCategories
}




