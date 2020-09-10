package dev.stive.moviereviewer.ui.mainmoviepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.domain.MoviePagedDataSource
import kotlinx.coroutines.flow.Flow

class MovieViewModel : ViewModel() {

    // List of movies
    var mLstMovies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        MoviePagedDataSource()
    }.flow.cachedIn(viewModelScope)

    companion object{
        // Number of movies in one page from themoviedb.org
        private const val PAGE_SIZE = 20
    }
}