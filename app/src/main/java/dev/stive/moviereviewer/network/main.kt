package dev.stive.moviereviewer.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun main(){
    NetworkService.jsonPlaceHolderApi.getPostWithID(1).enqueue(object : Callback<Post>{
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            val post: Post? = response.body()
            println("Id:${post?.id},\nTitle:${post?.title},\nBody:${post?.body}")
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {
            println("Error ${t.stackTrace}")
        }
    })
}