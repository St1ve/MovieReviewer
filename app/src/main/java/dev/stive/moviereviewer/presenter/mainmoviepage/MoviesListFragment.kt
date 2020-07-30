package dev.stive.moviereviewer.presenter.mainmoviepage

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.presenter.MainActivity.Companion.lstMovies
import dev.stive.moviereviewer.presenter.recyclerview.adapters.MovieAdapter
import dev.stive.moviereviewer.presenter.recyclerview.adapters.MoviePagedAdapter

class MoviesListFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels<MovieViewModel>()

    private lateinit var srMovieList: SwipeRefreshLayout
    private lateinit var rvMovieItem: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    /*
    * Flag, which shows, when films is loading
    * true - loading in progress
    * false - we didn't loading anything
    */
    private var isLoading = false

    //Current position of recyclerview
    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    //TODO Добавить свайп ту рефреш и подгрузку фильмов
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        srMovieList = view.findViewById<SwipeRefreshLayout>(R.id.srFragmentMovies)

        initRecyclerView(view)
        setObservers(view)
        setRecyclerViewDivider()
    }

    private fun setObservers(view: View) {
        movieViewModel.error.observe(viewLifecycleOwner, Observer { eventError ->
            eventError.getContentIfNotHandled()?.let { error ->
                Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show()
            }
        })

        movieViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            this.isLoading = it
        })
    }

    private fun setRecyclerViewDivider() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItem.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
    }

    private fun initRecyclerView(view: View) {
        val adapter = MoviePagedAdapter(view,
            LayoutInflater.from(context),
            object :
                MovieAdapter.IMovieItemActions {
                override fun openMovieDetail(movieData: Movie) {
                    val bundleMovieData: Bundle = bundleOf("movieData" to movieData)
                    findNavController().navigate(
                        R.id.action_home_destination_to_movie_detail_destination,
                        bundleMovieData
                    )
                }

                override fun removeFromFavourite(movie: Movie) {
                    lstMovies[lstMovies.indexOf(movie)].flagFavourite = false
                }
            }
        )

        movieViewModel.mLstMovies.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        rvMovieItem.adapter = adapter
    }
}