package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
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
import dev.stive.moviereviewer.recyclerMovie.MovieItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if ((savedInstanceState != null) && (savedInstanceState.containsKey(KEY_LST_FAVOURITE_MOVIES))) {
            lstMovieFavourite =
                savedInstanceState.getParcelableArrayList<MovieItem>(KEY_LST_FAVOURITE_MOVIES)!!
        }

        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupNavigationMenu(navController)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT ){
            setupBottomNavigationMenu(navController)
        }
        else{
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
        outState.putIntegerArrayList(KEY_LST_KEYS_MOVIES, lstKeysFavouriteMovies)
    }

//    override fun onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START))
//            drawer.closeDrawer(GravityCompat.START)
//        else
//            super.onBackPressed()
//        showQuitDialog()
//    }

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

    companion object {
        const val KEY_MOVIE_DETAIL_DATA = "MovieDetailData"
        const val KEY_LST_FAVOURITE_MOVIES = "lstFavouriteMovies"
        const val KEY_LST_KEYS_MOVIES = "lstKeysFavouriteMovies"
        var lstMovieFavourite = ArrayList<MovieItem>()
        var lstKeysFavouriteMovies = ArrayList<Int>()
    }

}
