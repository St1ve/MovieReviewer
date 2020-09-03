package dev.stive.moviereviewer.network

import dev.stive.moviereviewer.data.MovieResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): MovieResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY: String = "52b2f7aa49c9d92b819ea6e63d084edf"

        fun create(): MovieService {

            val client = OkHttpClient().newBuilder().addInterceptor { chain ->
                val originalRequest: Request = chain.request()
                val originalUrl = originalRequest.url

                val newHttpUrl = originalUrl.newBuilder().addQueryParameter("api_key",
                    API_KEY
                ).addQueryParameter("language", Locale.getDefault().language).build()

                val requestBuilder: Request.Builder = originalRequest.newBuilder().url(newHttpUrl)
                val newRequest = requestBuilder.build()

                chain.proceed(newRequest)
            }.build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }
}