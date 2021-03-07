package com.example.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardData(val category: String, val movieData: List<MovieData>) : Parcelable

fun getCardData(): List<CardData> = mutableListOf<CardData>().let { cardData ->
    getMovieCategories().also {
        it.indices.forEach { i ->
            cardData.add(CardData(it[i], getMovieDataCard(it[i])))
        }
    }
    return cardData
}

private fun getMovieDataCard(category: String): List<MovieData> =
    mutableListOf<MovieData>().let { movies ->
        getMovieData().also {
            it.indices.forEach { i ->
                if (it[i].category == category) {
                    movies.add(it[i])
                }
            }
        }
        return movies
    }

private fun getMovieCategories(): List<String> = mutableListOf<String>().let { movieCategories ->
    getMovieData().also {
        it.indices.forEach { i ->
            if (!movieCategories.contains(it[i].category)) {
                movieCategories.add(it[i].category)
            }
        }
    }
    return movieCategories
}





