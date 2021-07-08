package ru.tashkent.hedghogtechtestcode.ui.feature.jokeslist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.tashkent.hedghogtechtestcode.R
import ru.tashkent.hedghogtechtestcode.data.local.JokeView
import ru.tashkent.hedghogtechtestcode.data.remote.Joke
import ru.tashkent.hedghogtechtestcode.databinding.FragmentJokesListBinding
import ru.tashkent.hedghogtechtestcode.exception.Failure
import ru.tashkent.hedghogtechtestcode.ui.core.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class JokesListFragment : BaseFragment<FragmentJokesListBinding>() {

    private val viewModel: JokesListViewModel by viewModels()

    @Inject
    lateinit var jokesAdapter: JokesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.failure.observe(viewLifecycleOwner, ::handleFailure)
        viewModel.jokes.observe(viewLifecycleOwner, ::handleJokesList)
        Log.d("StartDebug", "list: ${viewModel.jokes.value}")

        binding.btnReload.setOnClickListener {
            viewModel.loadJokes(binding.etCount.text.toString())
        }
    }

    private fun setupRecyclerView() {
        binding.rvJokes.adapter = jokesAdapter
        binding.rvJokes.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
    }

    private fun handleJokesList(jokes: List<JokeView>?) {
        jokesAdapter.submitList(jokes)
        binding.etCount.clearFocus()
        hideKeyboard()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            Failure.NetworkFailure -> snackbar(R.string.error_network)
            Failure.ServerFailure -> snackbar(R.string.error_server)
            Failure.NumberFormatFailure -> snackbar(R.string.error_number_format)
            else -> throw IllegalStateException()
        }
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }
}