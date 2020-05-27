package dev.stive.moviereviewer.recyclerMovie

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstMovies
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
        iMovieItemActions: MovieAdapter.IMovieItemActions
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

        chMovieFavourite.isChecked = movie.flagFavourite

        chMovieFavourite.setOnClickListener {
            if (!chMovieFavourite.isChecked) {
                iMovieItemActions.removeFromFavourite(movie)
                Snackbar.make(
                    view,
                    "Movie ${movie.title} was deleted from favourite",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                lstMovies[position].flagFavourite = true
                Snackbar.make(
                    view,
                    "Movie ${movie.title} was added to favourite",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}