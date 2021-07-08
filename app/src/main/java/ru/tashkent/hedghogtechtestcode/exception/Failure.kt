package ru.tashkent.hedghogtechtestcode.exception

sealed class Failure {

    object NetworkFailure : Failure()

    object ServerFailure : Failure()

    object NumberFormatFailure : Failure()
}
