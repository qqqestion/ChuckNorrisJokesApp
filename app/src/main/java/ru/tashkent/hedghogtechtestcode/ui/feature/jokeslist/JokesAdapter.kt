package ru.tashkent.hedghogtechtestcode.ui.feature.jokeslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.tashkent.hedghogtechtestcode.data.local.JokeView
import ru.tashkent.hedghogtechtestcode.databinding.ItemJokeBinding
import javax.inject.Inject

class JokesAdapter @Inject constructor() :
    ListAdapter<JokeView, JokesAdapter.ViewHolder>(JokeView.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemJokeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: JokeView) {
            with(binding) {
                tvJoke.text = joke.joke
            }
        }
    }
}