package dev.stive.moviereviewer.ui.mainmoviepage

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.R
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.ui.recyclerview.adapters.MoviePagedAdapter
import kotlinx.android.synthetic.main.fragment_movies_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels<MovieViewModel>()

    private lateinit var srMovieList: SwipeRefreshLayout
    private lateinit var rvMovieItem: RecyclerView

    private var currentPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState?.containsKey(KEY_RECYCLERVIEW_POSITION) == true)
            currentPosition = savedInstanceState.getInt(KEY_RECYCLERVIEW_POSITION)

        rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        srMovieList = view.findViewById<SwipeRefreshLayout>(R.id.srFragmentMovies)

        initRecyclerView(view)
        setRecyclerViewDivider()
    }

    override fun onStop() {
        super.onStop()
        currentPosition =
            (rvMovieItem.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_RECYCLERVIEW_POSITION, currentPosition)
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
                    Log.d("OnClickFavourite", "Adding to favourite ....")
                }
            }
        )

        srMovieList.setOnRefreshListener {
            movieAdapter.refresh()
        }

        movieAdapter.addLoadStateListener { loadState ->
            pbLoading.isVisible =
                loadState.source.refresh is LoadState.Loading && rvMovieItem.isEmpty()
            srMovieList.isRefreshing =
                loadState.source.refresh is LoadState.Loading && !rvMovieItem.isEmpty()

            val errorState = loadState.refresh as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Snackbar.make(
                    view,
                    "Error: ${it.error}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        rvMovieItem.adapter = movieAdapter

        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            movieAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { rvMovieItem.scrollToPosition(0) }
        }

        lifecycleScope.launch {
            movieViewModel.mLstMovies.collectLatest {
                movieAdapter.submitData(it)
            }
        }

        rvMovieItem.scrollToPosition(currentPosition)
    }

    companion object {
        private const val KEY_RECYCLERVIEW_POSITION = "recyclerViewPosition"
    }
}