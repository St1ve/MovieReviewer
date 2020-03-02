package dev.stive.moviereviewer.recyclerMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class MovieAdapter(val view: View, val inflater: LayoutInflater, val lstMoviesItems: List<MovieItem>, val flagFavourite:Boolean, val iMovieItemActions: IMovieItemActions) :
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
        holder.bind(view,lstMoviesItems[position], position, iMovieItemActions, flagFavourite)
    }

    interface IMovieItemActions {
        fun notifyDelete(position:Int)
        fun openMovieDetail(movieData:MovieItem)
    }
}