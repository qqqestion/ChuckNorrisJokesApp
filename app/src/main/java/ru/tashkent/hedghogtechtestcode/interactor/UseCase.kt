package ru.tashkent.hedghogtechtestcode.interactor

import kotlinx.coroutines.*
import ru.tashkent.hedghogtechtestcode.exception.Failure
import ru.tashkent.hedghogtechtestcode.functional.Either
import ru.tashkent.hedghogtechtestcode.util.AppCoroutineDispatchers

abstract class UseCase<out Type, in Params>(
    private val dispatchers: AppCoroutineDispatchers
) {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope = GlobalScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val job = scope.async(dispatchers.io) { run(params) }
        scope.launch(dispatchers.main) { onResult.invoke(job.await()) }
    }

    class None
}