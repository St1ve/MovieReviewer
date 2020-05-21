package dev.stive.moviereviewer

import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import dev.stive.moviereviewer.recyclerMovie.MovieItem

class MoviesListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    var listener: MovieAdapter.IOnMovieDetailOpen? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            object : MovieAdapter.IOnItemDelete {
                override fun onItemDelete(position: Int) {
                    adapter.notifyItemRemoved(position)
                }
            },
            listener
        )

        val rvMovieItem = view.findViewById<RecyclerView>(R.id.rvMovies)
        rvMovieItem.adapter = adapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItem.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
    }

    companion object {
        const val TAG_FRAGMENT = "MoviesListFragment"
        const val TAG_BACKSTACK = "MoviesListFragmentBackStack"
    }
}