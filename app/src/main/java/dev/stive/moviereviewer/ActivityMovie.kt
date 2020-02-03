package dev.stive.moviereviewer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import dev.stive.moviereviewer.MainActivity.Companion.MOVIE_DATA

class ActivityMovie : AppCompatActivity() {

    private lateinit var imageMovie: ImageView
    private lateinit var txtMovieTitle: TextView
    private lateinit var txtMovieDescription: TextView
    private lateinit var chkFavouriteMovie: CheckBox
    private lateinit var etMovieComment: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        imageMovie = findViewById(R.id.imgMovie)
        txtMovieTitle = findViewById(R.id.txtTitleMovie)
        txtMovieDescription = findViewById(R.id.txtMovieDescription)
        chkFavouriteMovie = findViewById(R.id.chkAddToFavourite)
        etMovieComment = findViewById(R.id.etMovieComment)

        val value = getIntent().getParcelableExtra<PassData>(MOVIE_DATA)
        val movieName = value?.movieName

        Toast.makeText(this, "Movie title:$movieName", Toast.LENGTH_LONG).show()

        txtMovieTitle.text = value?.movieName
        imageMovie.setImageResource(value.imgResId)

        when (movieName) {
            getString(R.string.who_am_i) -> txtMovieDescription.text = getString(R.string.description_who_am_i_movie)
            getString(R.string.iron_man_3) -> txtMovieDescription.text = getString(R.string.description_iron_man_movie)
            getString(R.string.accountent) -> txtMovieDescription.text = getString(R.string.description_accountent)
            else -> txtMovieDescription.text = "Description is missing"
        }

        val intent = Intent()
        //Set default values
        intent.putExtra(MainActivity.MOVIE_COMMENT, "" )
        intent.putExtra(MainActivity.MOVIE_FAVOURITE_STATE,chkFavouriteMovie.isChecked())
        setResult(Activity.RESULT_OK, intent)

        etMovieComment.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                intent.putExtra(MainActivity.MOVIE_COMMENT, etMovieComment.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.i("MainActivity", "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.i("MainActivity", "onTextChanged")
            }

        })

        chkFavouriteMovie.setOnClickListener {
            intent.putExtra(MainActivity.MOVIE_FAVOURITE_STATE, chkFavouriteMovie.isChecked)
        }
    }
}
