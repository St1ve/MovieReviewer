package dev.stive.moviereviewer.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.ui.recyclerview.holders.MovieViewHolder

class MoviePagedAdapter(private val view: View, private val inflater: LayoutInflater,private val iMovieItemActions: IMovieItemActions) :
    PagingDataAdapter<Movie, MovieViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            inflater.inflate(
                R.layout.item_movies,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(
            view,
            movie?: Movie(id = 0, title = "Title", overview = "Overview"),
            position,
            iMovieItemActions)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem

        }
    }

    interface IMovieItemActions {
        fun openMovieDetail(movieData: Movie)
        fun onFavouriteClick(movie: Movie)
    }
}
