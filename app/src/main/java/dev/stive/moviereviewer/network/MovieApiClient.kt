package dev.stive.moviereviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY: String = "52b2f7aa49c9d92b819ea6e63d084edf"
    const val LAGUAGE_US: String = "en-US"
    const val LAGUAGE_RUS: String = "ru"

    val apiClient: MovieApiInterface by lazy {

        val retrofit = Retrofit.
                        Builder().
                        baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }

//    val retrofit: Retrofit.Builder by lazy {
//        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
//    }
//
//    val apiService: ApiInterface by lazy {
//        retrofit.build().create(ApiInterface::class.java)
//    }
}