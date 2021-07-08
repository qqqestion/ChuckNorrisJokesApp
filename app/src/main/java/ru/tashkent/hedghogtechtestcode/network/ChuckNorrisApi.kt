package ru.tashkent.hedghogtechtestcode.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.tashkent.hedghogtechtestcode.data.remote.JokesResponse

interface ChuckNorrisApi {

    @GET("jokes/random/{number}")
    suspend fun jokes(@Path("number") numberOfJokes: Int): Response<JokesResponse>
}