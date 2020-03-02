package dev.stive.moviereviewer.recyclerMovie

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieItem(val id: Int,val title: String, val description: String, val poster: Bitmap) : Parcelable