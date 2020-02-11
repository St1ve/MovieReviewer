package dev.stive.moviereviewer.recyclerMovie

import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.SparseBooleanArrayParcelable

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.txtTitleMovie)
    val descriptionTv: TextView = itemView.findViewById(R.id.txtDescriptionMovie)
    val imagePosterTv: ImageView = itemView.findViewById(R.id.imgPosterMovie)
    val chMovieFavourite: CheckBox = itemView.findViewById(R.id.chkAddToFavourite)

    fun bind(movieItem: MovieItem, position : Int, itemStateArray: SparseBooleanArrayParcelable) {
        titleTv.text = movieItem.title
        descriptionTv.text = movieItem.description
        imagePosterTv.setImageBitmap(movieItem.poster)

        chMovieFavourite.isChecked = itemStateArray.get(position)
        Log.d("Favourite", "ViewHolder:$itemStateArray")

        chMovieFavourite.setOnClickListener {
            if (chMovieFavourite.isChecked) {
                lstMovieFavourite.add(movieItem)
                itemStateArray.put(position,true)
            }
            else{
                lstMovieFavourite.removeAt(lstMovieFavourite.indexOf(movieItem))
                itemStateArray.put(position,false)
            }

            Log.d(
                "ListFavouriteMovies", "List of favourite movies: ${lstMovieFavourite.size}"
            )
        }
    }
}