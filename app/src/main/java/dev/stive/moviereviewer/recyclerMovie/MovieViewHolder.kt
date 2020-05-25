package dev.stive.moviereviewer.recyclerMovie

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.MainActivity.Companion.removeMovieFromFavourite
import dev.stive.moviereviewer.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    private val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    private val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    private val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)
    private val btnMovieDetail: Button = itemView.findViewById(R.id.btnDetailMovie)

    fun bind(
        view: View,
        movieItem: MovieItem,
        position: Int,
        iOnItemDelete: MovieAdapter.IOnItemDelete,
        iOnMovieDetailOpen: MovieAdapter.IOnMovieDetailOpen?,
        flagFavourite: Boolean
    ) {
        titleTv.text = movieItem.title
        descriptionTv.text = movieItem.description
        imagePosterTv.setImageBitmap(movieItem.poster)

        btnMovieDetail.setOnClickListener {
            iOnMovieDetailOpen?.onOpenMovieDetail(movieItem)
        }

        if (flagFavourite) {
            chMovieFavourite.isChecked = true

            chMovieFavourite.setOnClickListener {
                if (!chMovieFavourite.isChecked) {
                    removeMovieFromFavourite(movieItem.id)
                    chMovieFavourite.isChecked = false
                    iOnItemDelete.onItemDelete(position)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            chMovieFavourite.isChecked = checkForFavourite(movieItem.id)

            chMovieFavourite.setOnClickListener {
                if (chMovieFavourite.isChecked) {
                    lstMovieFavourite.add(movieItem)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was added to favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    removeMovieFromFavourite(movieItem.id)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun checkForFavourite(idMovie: Int):Boolean{
        for (movie in lstMovieFavourite){
            if (movie.id == idMovie){
                return true
            }
        }
        return false
    }
}