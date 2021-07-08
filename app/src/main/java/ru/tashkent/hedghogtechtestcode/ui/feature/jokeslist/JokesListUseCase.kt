package ru.tashkent.hedghogtechtestcode.ui.feature.jokeslist

import ru.tashkent.hedghogtechtestcode.data.remote.Joke
import ru.tashkent.hedghogtechtestcode.exception.Failure
import ru.tashkent.hedghogtechtestcode.functional.Either
import ru.tashkent.hedghogtechtestcode.interactor.UseCase
import ru.tashkent.hedghogtechtestcode.repository.MainRepository
import ru.tashkent.hedghogtechtestcode.util.AppCoroutineDispatchers
import javax.inject.Inject

class JokesListUseCase @Inject constructor(
    private val repository: MainRepository,
    dispatchers: AppCoroutineDispatchers
) : UseCase<List<Joke>, JokesListUseCase.Params>(dispatchers) {

    override suspend fun run(params: Params): Either<Failure, List<Joke>> {
        val numberOfJokes =
            params.rawNumber.toIntOrNull() ?: return Either.Left(Failure.NumberFormatFailure)
        return repository.jokes(numberOfJokes)
    }

    data class Params(val rawNumber: String)
}