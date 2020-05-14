package dev.stive.moviereviewer


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
import dev.stive.moviereviewer.recyclerMovie.MovieAdapter
import dev.stive.moviereviewer.recyclerMovie.MovieItem

/**
 * A simple [Fragment] subclass.
 */
class MoviesFavouriteListFragment : Fragment() {
    private lateinit var adapter: MovieAdapter
    var listener: MovieAdapter.IOnMovieDetailOpen? = null

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
            object : MovieAdapter.IOnItemDelete {
                override fun onItemDelete(position: Int) {
                    adapter.notifyItemRemoved(position)
                }
            },
            listener
        )

        val rvMovieItemFavourite =  view.findViewById<RecyclerView>(R.id.rvFavouriteMovies)
        rvMovieItemFavourite.adapter = adapter
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            rvMovieItemFavourite.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

    companion object{
        const val TAG = "MoviesFavouriteListFragment"
    }
}
