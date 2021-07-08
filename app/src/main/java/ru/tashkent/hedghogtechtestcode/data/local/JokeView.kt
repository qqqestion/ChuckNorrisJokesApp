package ru.tashkent.hedghogtechtestcode.data.local

import androidx.recyclerview.widget.DiffUtil

data class JokeView(
    val id: Int,
    val joke: String
) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JokeView>() {
            override fun areItemsTheSame(oldItem: JokeView, newItem: JokeView): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: JokeView, newItem: JokeView): Boolean {
                return oldItem == newItem
            }
        }
    }
}
