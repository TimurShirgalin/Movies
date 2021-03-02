package com.example.movies.model

interface CardSource {
    fun getCardData(position: Int): CardData?
    fun size(): Int
}