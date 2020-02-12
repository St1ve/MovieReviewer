package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if((savedInstanceState!= null) && (savedInstanceState.containsKey(KEY_LST_FAVOURITE_MOVIES))){
            lstMovieFavourite = savedInstanceState.getParcelable(KEY_LST_FAVOURITE_MOVIES)!!
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(KEY_LST_FAVOURITE_MOVIES, lstMovieFavourite)
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
        var lstMovieFavourite = SparseArrayMovieItemParcebale()
    }

}
