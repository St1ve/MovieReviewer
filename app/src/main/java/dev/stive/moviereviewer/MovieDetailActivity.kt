package dev.stive.moviereviewer

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dev.stive.moviereviewer.recyclerMovie.MovieItem

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var txtDescription: TextView
    private lateinit var imgMovie: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val dataFromMainActivity = getIntent().getParcelableExtra<MovieItem>(MainActivity.KEY_MOVIE_DETAIL_DATA)

        toolbar = findViewById(R.id.movieDetailToolBar)
        txtDescription = findViewById(R.id.txtMovieDescription)
        imgMovie = findViewById(R.id.imgMovie)

        toolbar.title = dataFromMainActivity?.title
        txtDescription.setText(dataFromMainActivity?.description)
        imgMovie.setImageResource(dataFromMainActivity.resIdPoster)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
