package dev.stive.moviereviewer

import android.os.Parcelable
import android.util.SparseArray
import dev.stive.moviereviewer.recyclerMovie.MovieItem
import kotlinx.android.parcel.Parcelize

@Parcelize
class SparseArrayMovieItemParcebale: SparseArray<MovieItem>(), Parcelable