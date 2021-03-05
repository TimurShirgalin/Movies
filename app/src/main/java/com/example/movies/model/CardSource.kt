package com.example.movies.model

interface CardSource {
    fun getDataFromLocalSource(): List<CardData>
}