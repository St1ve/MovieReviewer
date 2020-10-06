package dev.stive.moviereviewer.ui.mainmoviepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.domain.MoviePagedDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieViewModel : ViewModel() {

    // List of movies
    var mLstMovies: Flow<PagingData<UiModel>> = Pager( config = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = true
    )) {
        MoviePagedDataSource()
    }.flow.map {pagingData: PagingData<Movie> ->
        pagingData.map {
            UiModel.MovieItem(it)
        }
    }.map {
        it.insertSeparators { before, after ->
            if (after == null)
                return@insertSeparators null

            if (before == null)
                return@insertSeparators UiModel.SeparatorItem("${after.roundRating}.0+ rating")

            if (before.roundRating > after.roundRating){
                if (after.roundRating >= 1){
                    UiModel.SeparatorItem("${after.roundRating}.0+ rating")
                } else {
                    UiModel.SeparatorItem("-1.0 rating")
                }
            } else {
                null
            }
        }
    }.cachedIn(viewModelScope)

    companion object{
        // Number of movies in one page from themoviedb.org
        private const val PAGE_SIZE = 20
    }
}

sealed class UiModel {
    data class MovieItem(val movie: Movie) : UiModel()
    data class SeparatorItem(val rating: String) : UiModel()
}

private val UiModel.MovieItem.roundRating: Int
    get() = this.movie.voteAverage?.toInt()?: 0