package dev.stive.moviereviewer

import android.app.Application
import dev.stive.moviereviewer.network.MovieService

class App : Application() {

    lateinit var movieService: MovieService

    override fun onCreate() {
        super.onCreate()

        instance = this

        initMovieService()
    }

    private fun initMovieService() {
        movieService = MovieService.create()
    }

    companion object {
        var instance: App? = null
            private set
    }
}