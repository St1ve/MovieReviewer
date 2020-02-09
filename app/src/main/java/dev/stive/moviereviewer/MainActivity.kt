package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var chFavouriteMovieStates: SparseBooleanArrayParcelable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chFavouriteMovieStates = SparseBooleanArrayParcelable()

        val lstMovies = arrayListOf(
            MovieItem(
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            ),
            MovieItem(
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            ),
            MovieItem(
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            )
        )

        if ((savedInstanceState != null) && (savedInstanceState.containsKey(KEY_LST_CHECKBOX_FAVOURITE))){
            chFavouriteMovieStates = savedInstanceState.getParcelable(
                KEY_LST_CHECKBOX_FAVOURITE
            )!!
        }



        initRecyclerMovie(lstMovies)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lstMovieFavourite = savedInstanceState.getParcelableArrayList<MovieItem>(
            KEY_LST_FAVOURITE_MOVIES
        )!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        chFavouriteMovieStates = adapter.itemStateArray

        outState.putParcelableArrayList(KEY_LST_FAVOURITE_MOVIES, lstMovieFavourite)
        outState.putParcelable(KEY_LST_CHECKBOX_FAVOURITE, chFavouriteMovieStates)
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

    fun initRecyclerMovie(lstMovies: List<MovieItem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)

        val layoutManager: LinearLayoutManager

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }

        recyclerView.layoutManager = layoutManager

        Log.d("Main", "Main initRecyclerMovie value:$chFavouriteMovieStates")

        adapter = MovieAdapter(
            LayoutInflater.from(this), lstMovies,
            chFavouriteMovieStates
        )

        recyclerView.adapter = adapter
    }

    companion object {
        val KEY_LST_CHECKBOX_FAVOURITE = "chFavouriteMovies"
        val KEY_LST_FAVOURITE_MOVIES = "lstFavouriteMovies"
        var lstMovieFavourite = ArrayList<MovieItem>()
    }
}
