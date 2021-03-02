package com.example.movies.model

class CardSourceImpl : CardSource {

    private var dataSource: MutableList<CardData> = mutableListOf()

    fun init(): CardSourceImpl {
        for (i in MovieRes().category.indices) {
            dataSource.add(CardData(MovieRes().category[i], MovieRes().title[i]))
        }
        return this
    }

    override fun getCardData(position: Int): CardData = dataSource[position]

    override fun size(): Int = dataSource.size
}