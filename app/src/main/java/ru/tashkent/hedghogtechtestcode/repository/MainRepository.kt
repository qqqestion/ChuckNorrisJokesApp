package ru.tashkent.hedghogtechtestcode.repository

import ru.tashkent.hedghogtechtestcode.data.remote.Joke
import ru.tashkent.hedghogtechtestcode.exception.Failure
import ru.tashkent.hedghogtechtestcode.functional.Either
import ru.tashkent.hedghogtechtestcode.network.ChuckNorrisApi
import ru.tashkent.hedghogtechtestcode.network.NetworkHandler
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val chuckNorrisApi: ChuckNorrisApi,
    private val networkHandler: NetworkHandler
) {

    suspend fun jokes(numberOfJokes: Int): Either<Failure, List<Joke>> {
        return if (networkHandler.isNetworkAvailable()) {
            try {
                val resp = chuckNorrisApi.jokes(numberOfJokes)
                if (!resp.isSuccessful || resp.body()?.type != "success") {
                    Either.Left(Failure.ServerFailure)
                }
                Either.Right(resp.body()?.jokes ?: emptyList())
            } catch (e: Throwable) {
                Either.Left(Failure.ServerFailure)
            }
        } else {
            Either.Left(Failure.NetworkFailure)
        }
    }
}