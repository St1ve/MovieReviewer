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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.data.MovieResponse
import dev.stive.moviereviewer.network.MovieApiClient
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MoviesListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    private lateinit var srMovieList: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        val srMovieList = view.findViewById<SwipeRefreshLayout>(R.id.srFragmentMovies)

        srMovieList.setOnRefreshListener {
            setMoviesToRecyclerView(rvMovieItem, view)
            srMovieList.isRefreshing = false
        }

        setMoviesToRecyclerView(rvMovieItem, view)


        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItem.addItemDecoration(
                DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
            )
    }

    private fun setMoviesToRecyclerView(
        rvMovieItem: RecyclerView,
        view: View
    ) {
        val call: Call<MovieResponse> = MovieApiClient.apiClient.getTopRatedMovies(
            MovieApiClient.API_KEY,
            Locale.getDefault().language
        )

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()!!.results

                rvMovieItem.adapter = MovieAdapter(
                    view,
                    LayoutInflater.from(context),
                    movies,
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
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}