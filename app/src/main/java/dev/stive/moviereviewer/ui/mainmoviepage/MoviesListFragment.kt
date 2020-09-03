package dev.stive.moviereviewer.ui.mainmoviepage

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.ui.recyclerview.adapters.MoviePagedAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels<MovieViewModel>()
    private val movieFavouriteViewModel: FavouriteMovieViewModel by activityViewModels<FavouriteMovieViewModel>()

    private lateinit var srMovieList: SwipeRefreshLayout
    private lateinit var rvMovieItem: RecyclerView

    /**
    * Flag, which shows, when films is loading
    * true - loading in progress
    * false - don't loading movies
    */
    private var isLoading = true

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
        val movieAdapter = MoviePagedAdapter(view,
            LayoutInflater.from(context),
            object :
                MoviePagedAdapter.IMovieItemActions {
                override fun openMovieDetail(movieData: Movie) {
                    val bundleMovieData: Bundle = bundleOf("movieData" to movieData)
                    findNavController().navigate(
                        R.id.action_home_destination_to_movie_detail_destination,
                        bundleMovieData
                    )
                }

                override fun onFavouriteClick(movie: Movie) {
//                    movieViewModel.test(movie)
//                    movieFavouriteViewModel.addToFavourite(movie)
                    Log.d("OnClickFavourite","Adding to favourite ....")
                }
            }
        )

        rvMovieItem.adapter = movieAdapter

        lifecycleScope.launch {
            movieViewModel.mLstMovies.collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }
}