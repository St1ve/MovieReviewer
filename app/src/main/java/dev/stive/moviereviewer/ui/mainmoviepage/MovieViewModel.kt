package dev.stive.moviereviewer.ui.mainmoviepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.domain.Event
import dev.stive.moviereviewer.domain.MoviePagedDataSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    /**Flag, which shows, when films is loading
     * True - loading in progress
     * False - we didn't loading anything
     **/
    private val mIsLoading = MutableLiveData<Boolean>(true)

    // Information about error for user
    private val mError = MutableLiveData<Event<String>>()

    // List of movies
    var mLstMovies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviePagedDataSource()
    }.flow.cachedIn(viewModelScope)

    val isLoading: LiveData<Boolean>
        get() = mIsLoading

    val error: LiveData<Event<String>>
        get() = mError

    companion object{
        // Number of movies in one page from themoviedb.org
        private const val PAGE_SIZE = 20
    }
}