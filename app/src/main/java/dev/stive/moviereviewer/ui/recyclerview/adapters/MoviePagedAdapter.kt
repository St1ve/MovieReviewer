package dev.stive.moviereviewer.ui.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.ui.mainmoviepage.UiModel
import dev.stive.moviereviewer.ui.recyclerview.holders.MovieViewHolder
import dev.stive.moviereviewer.ui.recyclerview.holders.SeparatorViewHolder

class MoviePagedAdapter(
    private val view: View,
    private val inflater: LayoutInflater,
    private val iMovieItemActions: IMovieItemActions
) :
    PagingDataAdapter<UiModel, ViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  if (viewType == R.layout.item_movies) {
            MovieViewHolder(
                inflater.inflate(
                    R.layout.item_movies,
                    parent,
                    false
                )
            )
        } else {
            SeparatorViewHolder.create(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.MovieItem -> R.layout.item_movies
            is UiModel.SeparatorItem -> R.layout.item_separator_rating
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        item.let {
            when (item) {
                is UiModel.MovieItem -> (holder as MovieViewHolder).bind(
                    view,
                    item.movie,
                    iMovieItemActions
                )
                is UiModel.SeparatorItem -> (holder as SeparatorViewHolder).bind(item.rating)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.MovieItem && newItem is UiModel.MovieItem &&
                        oldItem.movie.title == newItem.movie.title) ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
                                oldItem.rating == newItem.rating)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel) = oldItem == newItem

        }
    }

    interface IMovieItemActions {
        fun openMovieDetail(movieData: Movie)
        fun onFavouriteClick(movie: Movie)
    }
}
