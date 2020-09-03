package dev.stive.moviereviewer.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.stive.moviereviewer.R

class MoviesFavouriteListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_favourite_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val lstFavouritMovies = ArrayList<Movie>()
//        for (movie in lstMovies) {
//            if (movie.flagFavourite)
//                lstFavouritMovies.add(movie)
//        }
//
//        adapter = MovieAdapter(
//            view,
//            LayoutInflater.from(context),
//            object :
//                MovieAdapter.IMovieItemActions {
//                override fun openMovieDetail(movieData: Movie) {
//                    val bundleMovieData: Bundle = bundleOf("movieData" to movieData)
//                    findNavController().navigate(
//                        R.id.action_favourite_movies_destination_to_movie_detail_destination,
//                        bundleMovieData
//                    )
//                }
//
//                override fun removeFromFavourite(movie: Movie) {
//                    lstMovies[lstMovies.indexOf(movie)].flagFavourite = false
//                    val removedPosition = lstFavouritMovies.indexOf(movie)
//                    lstFavouritMovies.removeAt(removedPosition)
//                    adapter.notifyItemRemoved(removedPosition)
//                }
//            }
//        )
//
//        val rvMovieItemFavourite = view.findViewById<RecyclerView>(R.id.rvFavouriteMovies)
//        rvMovieItemFavourite.adapter = adapter
//        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
//            rvMovieItemFavourite.addItemDecoration(
//                DividerItemDecoration(
//                    context,
//                    DividerItemDecoration.VERTICAL
//                )
//            )
    }
}
