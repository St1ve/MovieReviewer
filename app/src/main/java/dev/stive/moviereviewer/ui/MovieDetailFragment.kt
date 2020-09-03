package dev.stive.moviereviewer.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import java.util.*

class MovieDetailFragment : Fragment() {

    private lateinit var txtMovieDescription: TextView
    private lateinit var imgMoviePoster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolBarDetail)

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        txtMovieDescription = view.findViewById(R.id.txtMovieDescription)
        imgMoviePoster = view.findViewById(R.id.imgMovie)

        val movieData: Movie = arguments?.getParcelable("movieData")!!

        Log.d("TestLocale", "Laguage:${Locale.getDefault().language}")

        if (Locale.getDefault().language.equals("en")) {
            toolbar.title = movieData.originalTitle
            txtMovieDescription.text = movieData.overview
        } else {
            toolbar.title = movieData.title
            txtMovieDescription.text = movieData.overview
        }

        Glide.with(view)
            .load(movieData.posterPath)
            .placeholder(R.drawable.ic_placeholder_image_black_32dp)
            .fitCenter()
            .into(imgMoviePoster)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
