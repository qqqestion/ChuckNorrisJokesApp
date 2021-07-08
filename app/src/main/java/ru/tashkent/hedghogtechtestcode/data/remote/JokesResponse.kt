package ru.tashkent.hedghogtechtestcode.data.remote

import com.google.gson.annotations.SerializedName

data class JokesResponse(
    val type: String,
    @SerializedName("value")
    val jokes: List<Joke>
)