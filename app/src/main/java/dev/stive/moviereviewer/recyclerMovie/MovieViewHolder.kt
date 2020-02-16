package dev.stive.moviereviewer.recyclerMovie

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstKeysFavouriteMovies
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
        imagePosterTv.setImageBitmap(movieItem.poster)

        btnMovieDetail.setOnClickListener {
            iMovieItemActions.OpenMovieDetail(movieItem)

        }

        if (flagFavourite) {
            chMovieFavourite.isChecked = true

            chMovieFavourite.setOnClickListener {
                if (!chMovieFavourite.isChecked) {
                    lstMovieFavourite.removeAt(position)
                    lstKeysFavouriteMovies.removeAt(position)
                    chMovieFavourite.isChecked = false
                    iMovieItemActions.NotifyDelete(position)
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.d("lstMovies", "value:${lstMovieFavourite}")
                }
            }
        } else {
            chMovieFavourite.isChecked = lstKeysFavouriteMovies.indexOf(position) != -1

            chMovieFavourite.setOnClickListener {
                if (chMovieFavourite.isChecked) {
                    lstMovieFavourite.add(movieItem)
                    lstKeysFavouriteMovies.add(position)

                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was added to favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    Log.d("lstMovies", "value:${lstMovieFavourite}")
                } else {
                    val removePosition = lstKeysFavouriteMovies.indexOf(position)
                    lstMovieFavourite.removeAt(removePosition)
                    lstKeysFavouriteMovies.removeAt(removePosition)
                    chMovieFavourite.isChecked = false
                    Snackbar.make(
                        view,
                        "Movie ${movieItem.title} was deleted from favourite",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}