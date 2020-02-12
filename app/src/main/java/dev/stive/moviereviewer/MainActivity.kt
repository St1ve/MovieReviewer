package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if ((savedInstanceState != null) && (savedInstanceState.containsKey(KEY_LST_FAVOURITE_MOVIES))) {
            lstMovieFavourite = savedInstanceState.getParcelable(KEY_LST_FAVOURITE_MOVIES)!!
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        setupNavigationMenu(navController)

        setupBottomNavigationMenu(navController)
    }

    private fun setupBottomNavigationMenu(controller: NavController) {
        nav_bottom_bar?.let {
            setupWithNavController(it, controller)
        }
    }

//    private fun setupNavigationMenu(controller: NavController) {
//        val sideNavView =findViewById<NavigationView>(R.id.nav_view)
//        sideNavView?.let {
//            setupWithNavController(it,controller)
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(KEY_LST_FAVOURITE_MOVIES, lstMovieFavourite)
        outState.putIntegerArrayList(KEY_LST_KEYS_MOVIES, lstKeysFavouriteMovies)
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

    companion object {
        const val KEY_LST_FAVOURITE_MOVIES = "lstFavouriteMovies"
        const val KEY_LST_KEYS_MOVIES = "lstKeysFavouriteMovies"
        var lstMovieFavourite = SparseArrayMovieItemParcebale()
        var lstKeysFavouriteMovies = ArrayList<Int>()
    }

}
