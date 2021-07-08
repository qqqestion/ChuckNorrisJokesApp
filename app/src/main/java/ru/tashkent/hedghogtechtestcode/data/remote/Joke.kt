package ru.tashkent.hedghogtechtestcode.data.remote

data class Joke(
    val categories: List<Any>,
    val id: Int,
    val joke: String
)