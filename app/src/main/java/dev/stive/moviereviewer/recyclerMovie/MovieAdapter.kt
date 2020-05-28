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
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*
    * true - show progressbar
    * false - hide progressbar
    */
    private var isLoading:Boolean = false

    fun setLoading(status:Boolean){
        isLoading = status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_ITEM -> MovieViewHolder(
                inflater.inflate(
                    R.layout.item_movies,
                    parent,
                    false
                )
            )
            else -> LoadingViewHolder(
                inflater.inflate(
                    R.layout.item_movie_footer,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = lstMoviesItems.size + 1 // +1 = CountItems + Footer

    override fun getItemViewType(position: Int): Int {
        return if (position == lstMoviesItems.size) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieViewHolder)
            holder.bind(view, lstMoviesItems[position], position, iMovieItemActions)
        if (holder is LoadingViewHolder)
            holder.bind(isLoading)
    }

    interface IMovieItemActions {
        fun openMovieDetail(movieData: Movie)
        fun removeFromFavourite(movie: Movie)
    }

    companion object {
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_FOOTER = 1
    }
}