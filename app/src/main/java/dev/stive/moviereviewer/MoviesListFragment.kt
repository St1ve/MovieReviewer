package dev.stive.moviereviewer

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import dev.stive.moviereviewer.MainActivity.Companion.lstMovies
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.data.MovieResponse
import dev.stive.moviereviewer.network.MovieApiClient
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MoviesListFragment : Fragment() {
    private lateinit var srMovieList: SwipeRefreshLayout
    private lateinit var rvMovieItem: RecyclerView

    /*
    * Flag, which shows, when films is loading
    * true - loading in progress
    * false - we didn't loading anything
    */
    private var isLoading = false
    //Current position of recyclerview
    private var currentPosition = 0
    //Shows, how many pages we already have loaded
    private var currentPages: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(KEY_CURRENT_POSITION))
                currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION)
            if (savedInstanceState.containsKey(KEY_CURRENT_PAGE))
                currentPosition = savedInstanceState.getInt(KEY_CURRENT_PAGE)
        }

        rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        srMovieList = view.findViewById<SwipeRefreshLayout>(R.id.srFragmentMovies)

        srMovieList.setOnRefreshListener {
            setMoviesToRecyclerView(view)
        }

        if (lstMovies.isEmpty()) {
            //setup empty recycler view (adding progress bar)
            setupRecyclerViewAdapter(view)
            //get movies from api
            setMoviesToRecyclerView(view)
        } else{
            setupRecyclerViewAdapter(view)
            rvMovieItem.scrollToPosition(currentPosition)
        }


        rvMovieItem.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                currentPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (currentPosition == lstMovies.size - 1 && !isLoading) {
                    setStatusLoading(true)
                    loadNextPage()
                }
            }
        })

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItem.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
    }

    private fun loadNextPage() {
        currentPages++

        val call: Call<MovieResponse> = MovieApiClient.apiClient.getTopRatedMovies(
            MovieApiClient.API_KEY,
            currentPages,
            Locale.getDefault().language
        )

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                lstMovies.addAll(response.body()!!.results)
                setStatusLoading(false)
                rvMovieItem.adapter?.notifyItemRangeChanged(currentPosition, lstMovies.size - 1)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                Snackbar.make(
                    rvMovieItem,
                    R.string.msg_error_cant_load_movies,
                    Snackbar.LENGTH_SHORT
                ).show()
                setStatusLoading(false)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_CURRENT_PAGE, currentPages)
        outState.putInt(KEY_CURRENT_POSITION, currentPosition)
    }

    private fun setMoviesToRecyclerView(
        view: View
    ) {
        val call: Call<MovieResponse> = MovieApiClient.apiClient.getTopRatedMovies(
            MovieApiClient.API_KEY,
            1,
            Locale.getDefault().language
        )

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                lstMovies = response.body()!!.results as ArrayList<Movie>

                setupRecyclerViewAdapter(view)
                Log.d("Test", "${lstMovies.size}")
                srMovieList.isRefreshing = false
                setStatusLoading(false)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                Snackbar.make(
                    rvMovieItem,
                    R.string.msg_error_no_internet,
                    Snackbar.LENGTH_SHORT
                ).show()
                srMovieList.isRefreshing = false
                setStatusLoading(false)
            }
        })
    }

    private fun setStatusLoading(status: Boolean) {
        this.isLoading = status
        (rvMovieItem.adapter as MovieAdapter).setLoading(status)

        val indexLastItem = (rvMovieItem.adapter as MovieAdapter).itemCount - 1
        (rvMovieItem.adapter as MovieAdapter).notifyItemChanged(indexLastItem)
    }

    private fun setupRecyclerViewAdapter(view: View) {
        rvMovieItem.adapter = MovieAdapter(
            view,
            LayoutInflater.from(context),
            lstMovies,
            object : MovieAdapter.IMovieItemActions {
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
    }

    companion object{
        private const val KEY_CURRENT_PAGE = "currentPage"
        private const val KEY_CURRENT_POSITION = "currentPosition"
    }
}