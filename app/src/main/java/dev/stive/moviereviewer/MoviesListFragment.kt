package dev.stive.moviereviewer

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovies
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter

class MoviesListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private lateinit var mainActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter(
            view,
            LayoutInflater.from(context),
            lstMovies,
            false,
            object : MovieAdapter.IMovieItemActions {
                override fun notifyDelete(position: Int) {
                    adapter.notifyItemRemoved(position)
                }

                override fun openMovieDetail(movieData: Movie) {
                    val bundleMovieData: Bundle = bundleOf("movieData" to movieData)
                    findNavController().navigate(
                        R.id.action_home_destination_to_movie_detail_destination,
                        bundleMovieData
                    )
                }
            }
        )

        val rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        rvMovieItem.adapter = adapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItem.addItemDecoration(
                DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
            )
    }
}