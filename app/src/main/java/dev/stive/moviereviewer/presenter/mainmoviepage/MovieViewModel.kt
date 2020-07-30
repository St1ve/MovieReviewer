package dev.stive.moviereviewer.presenter.mainmoviepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.stive.moviereviewer.App
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.presenter.Event

class MovieViewModel : ViewModel() {
    private val movieInteractor = App.instance!!.movieInteractor

    /**Flag, which shows, when films is loading
     * True - loading in progress
     * False - we didn't loading anything
     **/
    private val mIsLoading = MutableLiveData<Boolean>(false)

    // Information about error for user
    private val mError = MutableLiveData<Event<String>>()

    private var liveDataSource: LiveData<MoviePagedDataSource>

    // List of movies
    var mLstMovies: LiveData<PagedList<Movie>>

    init {
        val itemDataSourceFactory =
            MovieDataSourceFactory(object : MovieDataSourceFactory.IGetError {

                override fun onError(error: String) {
                    mError.postValue(Event(error))
                }

            })

        liveDataSource = itemDataSourceFactory.movieLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MoviePagedDataSource.PAGE_SIZE)
            .build()

        mLstMovies = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    val error: LiveData<Event<String>>
        get() = mError
}