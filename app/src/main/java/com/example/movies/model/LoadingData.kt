package com.example.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class GenresData(
    val genres: List<Genres>,
) : Parcelable

@Parcelize
data class Genres(
    val id: Int?,
    val name: String?,
) : Parcelable

@Parcelize
data class MoviesData(
    val results: List<Movies>,
) : Parcelable

@Parcelize
data class Movies(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val vote_average: Number?,
    val genre_ids: List<Int>?,
) : Parcelable

@Parcelize
data class Categories(
    val categoryId: Int?,
    val categoryName: String?,
    val movieList: List<Movies>,
) : Parcelable