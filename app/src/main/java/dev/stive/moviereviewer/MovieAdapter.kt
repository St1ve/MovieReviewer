package dev.stive.moviereviewer

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val inflater: LayoutInflater, val title: List<String>, val description: List<String>, val imagePoster: List<Bitmap>) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(inflater.inflate(R.layout.item_movies, parent, false))
    }

    override fun getItemCount() = title.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(title[position], description[position], imagePoster[position])
    }
}