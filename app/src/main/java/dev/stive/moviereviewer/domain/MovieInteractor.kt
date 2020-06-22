package dev.stive.moviereviewer.domain

import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.data.MovieResponse
import dev.stive.moviereviewer.network.MovieApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieInteractor(private val movieApiInterface: MovieApiInterface) {

    fun getMovies(page: Int, callback: IGetMovieCallBack) {
        movieApiInterface.getPopularMovies(page).enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    // TODO добавить кэш см. проект OTUSAAC
                    callback.onSuccess(response.body()!!.results)
                } else {
                    callback.onError("Error code:" + response.code().toString())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                callback.onError("Network error probably...")
            }

        })
    }

    interface IGetMovieCallBack {
        fun onSuccess(lstMovies: List<Movie>)
        fun onError(error: String)
    }
}