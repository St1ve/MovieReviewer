package dev.stive.moviereviewer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dev.stive.moviereviewer.MainActivity.Companion.lstMovieFavourite
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

        adapter = MovieAdapter(
            LayoutInflater.from(context),
            lstMovieFavourite,
            true,
            object: MovieAdapter.INotifyAdapterChanged {
                override fun NotifyDelete(position:Int) {
                    adapter.notifyItemRemoved(position)
                }
            }
        )

        view.findViewById<RecyclerView>(R.id.rvFavouriteMovies)?.adapter = adapter
    }
}
