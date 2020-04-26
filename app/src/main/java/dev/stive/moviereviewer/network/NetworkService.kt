package dev.stive.moviereviewer.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkService {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    val mRetrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    val jsonPlaceHolderApi: JSONPlaceHolderApi by lazy {
        mRetrofit.build().create(JSONPlaceHolderApi::class.java)
    }
}