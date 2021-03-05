package com.example.movies.model

class CardSourceImpl : CardSource {

    override fun getDataFromLocalSource(): List<CardData> {
        return getCardData()
    }
}