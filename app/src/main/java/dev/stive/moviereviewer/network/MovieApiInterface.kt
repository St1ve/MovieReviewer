package dev.stive.moviereviewer.network

import dev.stive.moviereviewer.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MovieResponse
}