package ru.tashkent.hedghogtechtestcode.ui.feature.jokeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.tashkent.hedghogtechtestcode.data.local.JokeView
import ru.tashkent.hedghogtechtestcode.data.remote.Joke
import ru.tashkent.hedghogtechtestcode.exception.Failure
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel
@Inject constructor(
    private val getJokes: JokesListUseCase
) : ViewModel() {

    private val _failure = MutableLiveData<Failure?>()
    val failure: LiveData<Failure?> = _failure

    private val _jokes = MutableLiveData<List<JokeView>?>()
    val jokes: LiveData<List<JokeView>?> = _jokes

    fun loadJokes(rawNumber: String) = getJokes.invoke(
        JokesListUseCase.Params(rawNumber), viewModelScope
    ) { it.fold(::handleFailure, ::handleList) }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

    private fun handleList(jokes: List<Joke>) {
        _jokes.value = jokes.map { JokeView(it.id, it.joke) }
    }
}