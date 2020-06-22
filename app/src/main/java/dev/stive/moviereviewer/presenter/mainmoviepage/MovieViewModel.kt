package dev.stive.moviereviewer.presenter.mainmoviepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.stive.moviereviewer.App
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.domain.MovieInteractor
import dev.stive.moviereviewer.presenter.Event

class MovieViewModel : ViewModel() {
    private val movieInteractor = App.instance!!.movieInteractor

    /*
    * Flag, which shows, when films is loading
    * true - loading in progress
    * false - we didn't loading anything
    */
    private val mIsLoading = MutableLiveData<Boolean>(false)
    // Shows, how many pages we already downloaded
    private var page = 1
    // Information about error for user
    private val mError = MutableLiveData<Event<String>>()
    // List of movies
    private val mLstMovies = MutableLiveData<List<Movie>>()

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    val error: LiveData<Event<String>>
        get() = mError

    val lstMovies: LiveData<List<Movie>>
        get() = mLstMovies

    //Getting data from remote data source (www.themoviedb.org)
    fun getRemoteMovies() {
        movieInteractor.getMovies(page, object : MovieInteractor.IGetMovieCallBack{
            override fun onSuccess(lstMovies: List<Movie>) {
                Log.d("Post","Posting new movies...")
                mLstMovies.postValue(lstMovies)
            }

            override fun onError(error: String) {
                Log.d("Post","Error")
                mError.postValue(Event(error))
            }
        })
    }

    fun increasePage(){
        page++
    }
}