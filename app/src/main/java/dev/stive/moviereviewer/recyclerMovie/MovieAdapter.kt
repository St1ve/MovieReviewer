package dev.stive.moviereviewer.recyclerMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class MovieAdapter(
    val view: View,
    val inflater: LayoutInflater,
    val lstMoviesItems: List<MovieItem>,
    val flagFavourite: Boolean,
    val iOnItemDelete: IOnItemDelete,
    val iOnMovieDetailOpen: IOnMovieDetailOpen?
) :
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

    override fun getItemCount() = lstMoviesItems.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(view, lstMoviesItems[position], position, iOnItemDelete, iOnMovieDetailOpen, flagFavourite)
    }

    interface IOnItemDelete {
        fun onItemDelete(position: Int)
    }

    interface IOnMovieDetailOpen {
        fun onOpenMovieDetail(movieData: MovieItem)
    }
}