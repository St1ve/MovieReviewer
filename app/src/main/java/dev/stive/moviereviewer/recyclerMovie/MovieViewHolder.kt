package dev.stive.moviereviewer.recyclerMovie

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.MainActivity.Companion.removeMovieFromFavourite
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    private val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    private val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    private val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)
    private val btnMovieDetail: Button = itemView.findViewById(R.id.btnDetailMovie)

    fun bind(
        view: View,
        movie: Movie,
        position: Int,
        iMovieItemActions: MovieAdapter.IMovieItemActions,
        flagFavourite: Boolean
    ) {
        titleTv.text = movie.title
        descriptionTv.text = movie.overview
        Glide.with(view)
            .load(movie.posterPath)
            .placeholder(R.drawable.ic_placeholder_image_black_32dp)
            .fitCenter()
            .into(imagePosterTv)

        btnMovieDetail.setOnClickListener {
            iMovieItemActions.openMovieDetail(movie)
        }

        if (flagFavourite) {
            chMovieFavourite.isChecked = true

            chMovieFavourite.setOnClickListener {
                if (!chMovieFavourite.isChecked) {
                    removeMovieFromFavourite(movie.id)
                    chMovieFavourite.isChecked = false
                    iMovieItemActions.notifyDelete(position)
                    Snackbar.make(
                        view,
                        "Movie ${movie.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            chMovieFavourite.isChecked = checkForFavourite(movie.id)

            chMovieFavourite.setOnClickListener {
                if (chMovieFavourite.isChecked) {
                    lstMovieFavourite.add(movie)
                    Snackbar.make(
                        view,
                        "Movie ${movie.title} was added to favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    removeMovieFromFavourite(movie.id)
                    Snackbar.make(
                        view,
                        "Movie ${movie.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun checkForFavourite(idMovie: Int): Boolean {
        for (movie in lstMovieFavourite) {
            if (movie.id == idMovie) {
                return true
            }
        }
        return false
    }
}