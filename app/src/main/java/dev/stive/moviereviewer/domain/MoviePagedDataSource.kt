package dev.stive.moviereviewer.domain

import androidx.paging.PagingSource
import dev.stive.moviereviewer.App
import dev.stive.moviereviewer.data.Movie
import dev.stive.moviereviewer.network.MovieApiClient
import retrofit2.HttpException
import java.io.IOException

class MoviePagedDataSource() : PagingSource<Int, Movie>() {

    private val movieService = App.instance!!.movieService

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: FIRST_PAGE
            val lstMovies = MovieApiClient.apiClient.getPopularMovies(position).results
//            val lstMovies = movieService.getPopularMovies(position).results

            LoadResult.Page(
                data = lstMovies,
                prevKey = if (position == FIRST_PAGE) null else position - 1,
                nextKey = if (lstMovies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 30
        private const val FIRST_PAGE = 1
    }
}