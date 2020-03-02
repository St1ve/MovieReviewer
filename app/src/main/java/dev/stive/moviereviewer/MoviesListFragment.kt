package dev.stive.moviereviewer

import android.content.res.Configuration
import android.graphics.BitmapFactory
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
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import dev.stive.moviereviewer.recyclerMovie.MovieItem
import kotlinx.android.synthetic.main.activity_main.*

class MoviesListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        (activity as AppCompatActivity?)!!.toolBar.setSubtitle("Movies")

        val lstMovies = arrayListOf(
            MovieItem(
                0,
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                1,
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                2,
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            ),
            MovieItem(
                3,
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                4,
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                5,
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            ),
            MovieItem(
                6,
                getString(R.string.who_am_i),
                getString(R.string.description_who_am_i_movie),
                BitmapFactory.decodeResource(resources, R.drawable.who_am_i)
            ),
            MovieItem(
                7,
                getString(R.string.accountent),
                getString(R.string.description_accountent),
                BitmapFactory.decodeResource(resources, R.drawable.accountent)
            ),
            MovieItem(
                8,
                getString(R.string.iron_man_3),
                getString(R.string.description_iron_man_movie),
                BitmapFactory.decodeResource(resources, R.drawable.ironman)
            )
        )

        adapter = MovieAdapter(
            view,
            LayoutInflater.from(context),
            lstMovies,
            false,
            object : MovieAdapter.IMovieItemActions {
                override fun notifyDelete(position: Int) {
                    adapter.notifyItemRemoved(position)
                }

                override fun openMovieDetail(movieData: MovieItem) {
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
            rvMovieItem.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }
}