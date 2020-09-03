package dev.stive.moviereviewer.ui.mainmoviepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.stive.moviereviewer.data.Movie

class FavouriteMovieViewModel : ViewModel() {

    private val mLstFavouriteMovies: MutableLiveData<ArrayList<Movie>> =
        MutableLiveData<ArrayList<Movie>>()

    val lstFavouriteMovies: LiveData<ArrayList<Movie>>
        get() = mLstFavouriteMovies

    fun addToFavourite(movie: Movie){
        mLstFavouriteMovies.value?.add(movie)
    }

    fun removeFromFavourite(movie: Movie) {
        mLstFavouriteMovies.value?.remove(movie)
    }
}