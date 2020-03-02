package dev.stive.moviereviewer


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
import dev.stive.moviereviewer.recyclerMovie.MovieItem

/**
 * A simple [Fragment] subclass.
 */
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
        val toolbar = view.findViewById<Toolbar>(R.id.movieDetailToolBar)

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        txtMovieDescription = view.findViewById(R.id.txtMovieDescription)
        imgMoviePoster = view.findViewById(R.id.imgMovie)

        val movieData: MovieItem = arguments?.getParcelable("movieData")!!
        Log.d("MovieDetail", "MovieData:$movieData")

        toolbar.title = movieData.title
        txtMovieDescription.text = movieData.description
        imgMoviePoster.setImageBitmap(movieData.poster)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
//        val activity = (activity as AppCompatActivity?)
//        activity?.supportActionBar
//        activity?.setSupportActionBar(toolbar)
//        activity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
