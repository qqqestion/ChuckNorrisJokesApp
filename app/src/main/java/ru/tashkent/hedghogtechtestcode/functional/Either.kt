package ru.tashkent.hedghogtechtestcode.functional

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()

    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isLeft get() = this is Left

    val isRight get() = this is Right

    fun fold(
        fnL: (L) -> Any, fnR: (R) -> Any
    ): Any = when (this) {
        is Left -> fnL(a)
        is Right -> fnR(b)
    }
}
