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
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import dev.stive.moviereviewer.recyclerMovie.MovieItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieAdapter.IOnMovieDetailOpen {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Restoring data from Bundle
        if ((savedInstanceState != null) && (savedInstanceState.containsKey(KEY_LST_FAVOURITE_MOVIES))) {
            lstMovieFavourite =
                savedInstanceState.getParcelableArrayList<MovieItem>(KEY_LST_FAVOURITE_MOVIES)!!
        }

        //Setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolBarMain)
        setSupportActionBar(toolbar)

        //Setup navigation menus
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val bottomNavMenu: BottomNavigationView = findViewById(R.id.nav_bottom_bar)
            bottomNavMenu.setOnNavigationItemSelectedListener { item -> setupItemMenuSelecter(item) }
        } else {
            //Setup navigation drawer
            val drawer = findViewById<DrawerLayout>(R.id.main_drawer_layout)
            val toggle = ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_closed
            )

            //Set listener for open/close drawer
            drawer.addDrawerListener(toggle)
            toggle.syncState()

            //Set clickListeners for drawer menus items
            nav_view.setNavigationItemSelectedListener { item ->
                setupItemMenuSelecter(item)
                drawer.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    //Set clickListener to menus elements
    private fun setupItemMenuSelecter(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_destination -> {
                openMoviesListFragment()
            }
            R.id.favourite_movies_destination -> {
                openFavouriteListFragment()
            }
        }
        return true
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is MoviesListFragment) {
            fragment.listener = this
        }
        if (fragment is MoviesFavouriteListFragment) {
            fragment.listener = this
        }
        super.onAttachFragment(fragment)
    }

    private fun openFavouriteListFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            MoviesFavouriteListFragment(),
            MoviesFavouriteListFragment.TAG
        ).commit()
    }

    //Make transition to MoviesListFragment
    private fun openMoviesListFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            MoviesListFragment(),
            MoviesListFragment.TAG
        ).commit()
    }

    //Make transition to MoviesListFragment
    private fun openMovieDetailFragment(item: MovieItem) {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            MovieDetailFragment.newInstance(item),
            MovieDetailFragment.TAG
        ).addToBackStack(null).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_LST_FAVOURITE_MOVIES, lstMovieFavourite)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments[0] is MoviesFavouriteListFragment || supportFragmentManager.fragments[0] is MoviesListFragment)
            showQuitDialog()
        else
            supportFragmentManager.popBackStack()
    }

    private fun showQuitDialog() {
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

    companion object {
        const val KEY_MOVIE_DETAIL_DATA = "MovieDetailData"
        const val KEY_LST_FAVOURITE_MOVIES = "lstFavouriteMovies"
        var lstMovieFavourite = ArrayList<MovieItem>()

        fun removeMovieFromFavourite(idMovie: Int) {
            for (movie in lstMovieFavourite) {
                if (movie.id == idMovie) {
                    lstMovieFavourite.remove(movie)
                    return
                }
            }
        }
    }

    override fun onOpenMovieDetail(movieData: MovieItem) {
        openMovieDetailFragment(movieData)
    }
}
