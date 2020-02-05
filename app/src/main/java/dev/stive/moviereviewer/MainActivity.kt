package dev.stive.moviereviewer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lstTitleMovies = arrayListOf(
            getString(R.string.who_am_i),
            getString(R.string.iron_man_3),
            getString(R.string.accountent),
            getString(R.string.who_am_i),
            getString(R.string.iron_man_3),
            getString(R.string.accountent),
            getString(R.string.who_am_i),
            getString(R.string.iron_man_3),
            getString(R.string.accountent)
        )

        val lstDescriptionMovie = arrayListOf(
            getString(R.string.description_who_am_i_movie),
            getString(R.string.description_iron_man_movie),
            getString(R.string.description_accountent),
            getString(R.string.description_who_am_i_movie),
            getString(R.string.description_iron_man_movie),
            getString(R.string.description_accountent),
            getString(R.string.description_who_am_i_movie),
            getString(R.string.description_iron_man_movie),
            getString(R.string.description_accountent)
        )

        val lstImagePosterMovie = arrayListOf<Bitmap>(
            BitmapFactory.decodeResource(getResources(), R.drawable.who_am_i),
            BitmapFactory.decodeResource(getResources(), R.drawable.ironman),
            BitmapFactory.decodeResource(getResources(), R.drawable.accountent),
            BitmapFactory.decodeResource(getResources(), R.drawable.who_am_i),
            BitmapFactory.decodeResource(getResources(), R.drawable.ironman),
            BitmapFactory.decodeResource(getResources(), R.drawable.accountent),
            BitmapFactory.decodeResource(getResources(), R.drawable.who_am_i),
            BitmapFactory.decodeResource(getResources(), R.drawable.ironman),
            BitmapFactory.decodeResource(getResources(), R.drawable.accountent)
        )

        initRecyclerMovie(lstTitleMovies, lstDescriptionMovie, lstImagePosterMovie)
    }

    override fun onBackPressed() {
        showQuitDialog()
    }

    fun showQuitDialog() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        Log.i("action", "Show quit dialog")

        val actionCancel = DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() }

        val actionAccept = DialogInterface.OnClickListener { dialog, which -> finishAffinity() }

        alertDialogBuilder.setMessage(getString(R.string.alert_dialog_quit_message))
        alertDialogBuilder.setTitle(getString(R.string.alert_dialog_quit_title))
        alertDialogBuilder.setNegativeButton("No", actionCancel)
        alertDialogBuilder.setPositiveButton("Yes", actionAccept)
        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }

    fun initRecyclerMovie(
        lstTitleMovies: List<String>,
        lstDescriptionMovie: List<String>,
        lstImagePosterMovie: List<Bitmap>
    ) {
        val recyclerView = findViewById<RecyclerView>(R.id.rvMovies)


        val layoutManager: LinearLayoutManager

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
        else {
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(
            LayoutInflater.from(this),
            lstTitleMovies,
            lstDescriptionMovie,
            lstImagePosterMovie
        )
    }
}
