package dev.stive.moviereviewer.presenter.mainmoviepage

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.stive.moviereviewer.data.Movie

class MovieDataSourceFactory(private val error: IGetError) : DataSource.Factory<Int, Movie>() {

    val movieLiveDataSource = MutableLiveData<MoviePagedDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MoviePagedDataSource(error)
        movieLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

    interface IGetError{
        fun onError(error: String)
    }
}