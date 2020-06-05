package dev.stive.moviereviewer.network

import dev.stive.moviereviewer.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>
}