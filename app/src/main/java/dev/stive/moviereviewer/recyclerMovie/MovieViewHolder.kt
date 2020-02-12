package dev.stive.moviereviewer.recyclerMovie

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)

    fun bind(movieItem: MovieItem, position : Int) {
        titleTv.text = movieItem.title
        descriptionTv.text = movieItem.description
        imagePosterTv.setImageBitmap(movieItem.poster)

//        Log.d("Favourite", "ViewHolder:$itemStateArray")

        chMovieFavourite.isChecked = lstMovieFavourite.get(position) != null

        chMovieFavourite.setOnClickListener {
            if (chMovieFavourite.isChecked) {
                lstMovieFavourite.put(position,movieItem)
            }
            else{
                lstMovieFavourite.remove(position)
                chMovieFavourite.isChecked = false
            }

            Log.d(
                "ListFavouriteMovies", "List of favourite movies: ${lstMovieFavourite.size()}"
            )
        }
    }
}