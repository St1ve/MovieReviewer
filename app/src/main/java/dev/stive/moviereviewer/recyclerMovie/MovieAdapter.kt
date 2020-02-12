package dev.stive.moviereviewer.recyclerMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class MovieAdapter(val inflater: LayoutInflater, val movieItem: List<MovieItem>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            inflater.inflate(
                R.layout.item_movies,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = movieItem.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        Log.d("Favourite", "MovieAdapter:$")
        holder.bind(movieItem[position], position)
    }
}