package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.data.MovieResponse
import dev.stive.moviereviewer.network.MovieApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MovieDetailFragment", "MainCreate")
        getPopularMovies()

        if ((savedInstanceState != null) && (savedInstanceState.containsKey(KEY_LST_FAVOURITE_MOVIES))) {
            lstMovieFavourite =
                savedInstanceState.getParcelableArrayList<Movie>(KEY_LST_FAVOURITE_MOVIES)!!
        }

        val toolbar = findViewById<Toolbar>(R.id.toolBarMain)
        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupNavigationMenu(navController)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupBottomNavigationMenu(navController)
        } else {
            drawer = findViewById<DrawerLayout>(R.id.main_drawer_layout)
            val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_closed
            )
            drawer.addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    private fun setupBottomNavigationMenu(controller: NavController) {
        nav_bottom_bar?.setupWithNavController(controller)
    }

    private fun setupNavigationMenu(controller: NavController) {
        nav_view?.setupWithNavController(controller)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelableArrayList(KEY_LST_FAVOURITE_MOVIES, lstMovieFavourite)
    }

    override fun onBackPressed() {
        showQuitDialog()
    }

    fun showQuitDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        val actionCancel = DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }

        val actionAccept = DialogInterface.OnClickListener { dialog, which -> finishAffinity() }

        alertDialogBuilder.setMessage(getString(R.string.alert_dialog_quit_message))
        alertDialogBuilder.setTitle(getString(R.string.alert_dialog_quit_title))
        alertDialogBuilder.setNegativeButton("No", actionCancel)
        alertDialogBuilder.setPositiveButton("Yes", actionAccept)
        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }


    fun getPopularMovies() {

        val call: Call<MovieResponse> = MovieApiClient.apiClient.getTopRatedMovies(
            MovieApiClient.API_KEY,
            MovieApiClient.LAGUAGE_RUS
        )

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                lstMovies = response.body()?.results as List<Movie>

                Log.d("MovieData", lstMovies[0]?.title)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    companion object {
        var lstMovies: List<Movie> = ArrayList<Movie>()

        const val KEY_MOVIE_DETAIL_DATA = "MovieDetailData"
        const val KEY_LST_FAVOURITE_MOVIES = "lstFavouriteMovies"
        var lstMovieFavourite = ArrayList<Movie>()

        fun removeMovieFromFavourite(idMovie: Int) {
            for (movie in lstMovieFavourite) {
                if (movie.id == idMovie) {
                    lstMovieFavourite.remove(movie)
                    return
                }
            }
        }
    }

}
