package dev.stive.moviereviewer.recyclerMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.R

class MovieAdapter(val inflater: LayoutInflater, val lstMoviesItems: List<MovieItem>, val flagFavourite:Boolean, val iNotifyDelete: INotifyAdapterChanged) :
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
        holder.bind(lstMoviesItems[position], position, iNotifyDelete, flagFavourite)
    }

    interface INotifyAdapterChanged {
        fun NotifyDelete(position:Int)
    }
}