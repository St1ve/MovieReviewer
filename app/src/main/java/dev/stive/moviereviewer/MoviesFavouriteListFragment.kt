package dev.stive.moviereviewer


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.KEY_MOVIE_DETAIL_DATA
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import dev.stive.moviereviewer.recyclerMovie.MovieItem

/**
 * A simple [Fragment] subclass.
 */
class MoviesFavouriteListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_favourite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            view,
            LayoutInflater.from(context),
            lstMovieFavourite,
            true,
            object : MovieAdapter.IMovieItemActions {
                override fun NotifyDelete(position: Int) {
                    adapter.notifyItemRemoved(position)
                }

                override fun OpenMovieDetail(movieData: MovieItem) {
                    val intent: Intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra(KEY_MOVIE_DETAIL_DATA,movieData)
                    startActivity(intent)
                }
            }
        )

        val rvMovieItemFavourite =  view.findViewById<RecyclerView>(R.id.rvFavouriteMovies)
        rvMovieItemFavourite.adapter = adapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItemFavourite.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }
}
