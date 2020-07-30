package dev.stive.moviereviewer.presenter.mainmoviepage

import android.util.Log
import androidx.paging.PageKeyedDataSource
import dev.stive.moviereviewer.App
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.domain.MovieInteractor

class MoviePagedDataSource(private val showError: MovieDataSourceFactory.IGetError): PageKeyedDataSource<Int, Movie>() {

    private val movieInteractor = App.instance!!.movieInteractor

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        movieInteractor.getMovies(FIRST_PAGE, object : MovieInteractor.IGetMovieCallBack {
            override fun onSuccess(lstMovies: List<Movie>?) {
                lstMovies?.let {
                    callback.onResult(lstMovies, null, FIRST_PAGE + 1)
                }
            }

            override fun onError(error: String) {
                showError.onError(error)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        movieInteractor.getMovies(params.key, object : MovieInteractor.IGetMovieCallBack {
            override fun onSuccess(lstMovies: List<Movie>?) {
                Log.d("Post", "Load before")
                val adjacentKey = if (params.key > 1) params.key - 1 else 0
                lstMovies?.let {
                    callback.onResult(lstMovies, adjacentKey)
                }
            }

            override fun onError(error: String) {
                showError.onError(error)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        movieInteractor.getMovies(params.key, object : MovieInteractor.IGetMovieCallBack {
            override fun onSuccess(lstMovies: List<Movie>?) {
                Log.d("Post", "Load after")
                lstMovies?.let {
                    callback.onResult(lstMovies, params.key + 1)
                }
            }

            override fun onError(error: String) {
                showError.onError(error)
            }
        })
    }

    companion object {
        const val PAGE_SIZE = 30
        private const val FIRST_PAGE = 1
    }
}