package dev.stive.moviereviewer.recyclerMovie

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)
    val btnMovieDetail: Button = itemView.findViewById(R.id.btnDetailMovie)

    fun bind(
        view: View,
        movieItem: MovieItem,
        position: Int,
        iMovieItemActions: MovieAdapter.IMovieItemActions,
        flagFavourite: Boolean
    ) {
        titleTv.text = movieItem.title
        descriptionTv.text = movieItem.description
        imagePosterTv.setImageResource(movieItem.resIdPoster)

        btnMovieDetail.setOnClickListener {
            iMovieItemActions.OpenMovieDetail(movieItem)
        }

        if (flagFavourite) {
            chMovieFavourite.isChecked = true

            chMovieFavourite.setOnClickListener {
                if (!chMovieFavourite.isChecked) {
                    RemoveMovieFromFavourite(movieItem.id)
                    chMovieFavourite.isChecked = false
                    iMovieItemActions.NotifyDelete(position)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            chMovieFavourite.isChecked = CheckForFavourite(movieItem.id)

            chMovieFavourite.setOnClickListener {
                if (chMovieFavourite.isChecked) {
                    lstMovieFavourite.add(movieItem)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was added to favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    RemoveMovieFromFavourite(movieItem.id)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun CheckForFavourite(idMovie: Int):Boolean{
        for (movie in lstMovieFavourite){
            if (movie.id == idMovie){
                return true
            }
        }
        return false
    }

    private fun RemoveMovieFromFavourite(idMovie:Int){
        for (movie in lstMovieFavourite){
            if (movie.id == idMovie){
                lstMovieFavourite.remove(movie)
                return
            }
        }
    }
}