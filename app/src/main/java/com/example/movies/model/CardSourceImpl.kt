package com.example.movies.model

class CardSourceImpl : CardSource {
    override fun getDataFromLocalSource() = getCardData()
}