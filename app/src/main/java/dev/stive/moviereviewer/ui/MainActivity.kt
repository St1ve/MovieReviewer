package dev.stive.moviereviewer.ui

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
import androidx.navigation.get
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBarMain)

        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        setupNavigationMenu(navController)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupBottomNavigationMenu(navController)
        } else {
            val toggle = ActionBarDrawerToggle(
                this,
                binding.mainDrawerLayout,
                binding.toolBarMain,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_closed
            )
            binding.mainDrawerLayout.addDrawerListener(toggle)
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

    override fun onBackPressed() {
        Log.d("OnBackPressed", "Graph:${navController.currentDestination}")
        val destination = navController.graph[R.id.movie_detail_destination]
        if (navController.currentDestination == destination)
            super.onBackPressed()
        else
            showQuitDialog()
    }

    private fun showQuitDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        val actionCancel = DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }

        val actionAccept = DialogInterface.OnClickListener { _, _ -> finishAffinity() }

        alertDialogBuilder.setMessage(getString(R.string.alert_dialog_quit_message))
        alertDialogBuilder.setTitle(getString(R.string.alert_dialog_quit_title))
        alertDialogBuilder.setNegativeButton("No", actionCancel)
        alertDialogBuilder.setPositiveButton("Yes", actionAccept)
        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }
}
