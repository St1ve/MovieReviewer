package dev.stive.moviereviewer.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object MovieApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY: String = "52b2f7aa49c9d92b819ea6e63d084edf"

    val apiClient: MovieApiInterface by lazy {

        val interceptor = OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest: Request = chain.request()
            val originalUrl = originalRequest.url()

            val newHttpUrl = originalUrl.newBuilder().addQueryParameter("api_key", API_KEY).addQueryParameter("language", Locale.getDefault().language).build()
            val requestBuilder: Request.Builder = originalRequest.newBuilder().url(newHttpUrl)
            val newRequest = requestBuilder.build()

            chain.proceed(newRequest)
        }.build()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(interceptor)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}