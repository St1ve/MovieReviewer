package dev.stive.moviereviewer

import android.app.Application
import dev.stive.moviereviewer.domain.MovieInteractor
import dev.stive.moviereviewer.network.MovieApiClient

class App : Application() {

    lateinit var movieInteractor: MovieInteractor

    override fun onCreate() {
        super.onCreate()

        instance = this

        initInteractor()
    }

    private fun initInteractor() {
        movieInteractor = MovieInteractor(MovieApiClient.apiClient)
    }

    companion object {
        var instance: App? = null
            private set
    }
}