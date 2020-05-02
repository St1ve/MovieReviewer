package dev.stive.moviereviewer.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL2: String = "https://api.themoviedb.org"
private const val PAGE: Int = 1
private const val API_KEY: String = "52b2f7aa49c9d92b819ea6e63d084edf"
private const val LAGUAGE: String = "en-US"
private const val LAGUAGE2: String = "ru"
private const val CATEGORY: String = "popular"

fun main() {
    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL2)
        .build()

    val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)

    val call: Call<MovieResults> = apiInterface.listOfMovies(CATEGORY, API_KEY, LAGUAGE2, PAGE)

    call.enqueue(object : Callback<MovieResults>{
        override fun onResponse(call: Call<MovieResults>, response: Response<MovieResults>) {
            val movieResults = response.body()
            val lstResults =  movieResults?.results
            print(lstResults?.get(0)?.title)
            print(lstResults?.get(0)?.originalTitle)
//            print(lstResults)
        }

        override fun onFailure(call: Call<MovieResults>, t: Throwable) {
            t.printStackTrace()
        }

    })
}