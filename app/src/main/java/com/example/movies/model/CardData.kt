package com.example.movies.model

data class CardData(private var category: String, private var title: String) {

    fun getCategory(): String = category

    fun getTitle(): String = title
}
