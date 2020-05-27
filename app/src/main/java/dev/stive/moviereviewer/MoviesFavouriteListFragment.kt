package dev.stive.moviereviewer


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovies
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter

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

        val lstFavouritMovies = ArrayList<Movie>()
        for (movie in lstMovies) {
            if (movie.flagFavourite)
                lstFavouritMovies.add(movie)
        }

        adapter = MovieAdapter(
            view,
            LayoutInflater.from(context),
            lstFavouritMovies,
            object : MovieAdapter.IMovieItemActions {
                override fun openMovieDetail(movieData: Movie) {
                    val bundleMovieData: Bundle = bundleOf("movieData" to movieData)
                    findNavController().navigate(
                        R.id.action_favourite_movies_destination_to_movie_detail_destination,
                        bundleMovieData
                    )
                }

                override fun removeFromFavourite(movie: Movie) {
                    lstMovies[lstMovies.indexOf(movie)].flagFavourite = false
                    val removedPosition = lstFavouritMovies.indexOf(movie)
                    lstFavouritMovies.removeAt(removedPosition)
                    adapter.notifyItemRemoved(removedPosition)
                }
            }
        )

        val rvMovieItemFavourite = view.findViewById<RecyclerView>(R.id.rvFavouriteMovies)
        rvMovieItemFavourite.adapter = adapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItemFavourite.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
    }
}
