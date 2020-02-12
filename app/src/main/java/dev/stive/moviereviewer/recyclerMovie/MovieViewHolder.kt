package dev.stive.moviereviewer.recyclerMovie

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstKeysFavouriteMovies
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)

    fun bind(movieItem: MovieItem, position: Int, notifyAdapterChanged: MovieAdapter.INotifyAdapterChanged, flagFavourite: Boolean) {
        titleTv.text = movieItem.title
        descriptionTv.text = movieItem.description
        imagePosterTv.setImageBitmap(movieItem.poster)

        if (flagFavourite) {
            chMovieFavourite.isChecked = true

            chMovieFavourite.setOnClickListener {
                if (!chMovieFavourite.isChecked) {
                    lstMovieFavourite.removeAt(position)
                    lstKeysFavouriteMovies.removeAt(position)
                    chMovieFavourite.isChecked = false
                    notifyAdapterChanged.NotifyDelete(position)
                    Log.d("lstMovies", "value:${lstMovieFavourite}")
                }
            }
        } else {
            chMovieFavourite.isChecked = lstKeysFavouriteMovies.indexOf(position) != -1

            chMovieFavourite.setOnClickListener {
                if (chMovieFavourite.isChecked) {
                    lstMovieFavourite.add(movieItem)
                    lstKeysFavouriteMovies.add(position)

                    Log.d("lstMovies", "value:${lstMovieFavourite}")
                } else {
                    val removePosition = lstKeysFavouriteMovies.indexOf(position)
                    lstMovieFavourite.removeAt(removePosition)
                    lstKeysFavouriteMovies.removeAt(removePosition)
                    chMovieFavourite.isChecked = false
                }
            }

            Log.d(
                "ListFavouriteMovies", "List of favourite movies: ${lstMovieFavourite.size}"
            )
        }
    }
}