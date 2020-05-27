package dev.stive.moviereviewer.recyclerMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie

class MovieAdapter(
    private val view: View,
    private val inflater: LayoutInflater,
    private val lstMoviesItems: List<Movie>,
    private val iMovieItemActions: IMovieItemActions
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
        holder.bind(view, lstMoviesItems[position], position, iMovieItemActions)
    }

    interface IMovieItemActions {
        fun openMovieDetail(movieData: Movie)

        fun removeFromFavourite(movie: Movie)
    }
}