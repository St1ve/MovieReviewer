package dev.stive.moviereviewer

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieItem(val title: String, val description: String, val poster: Bitmap) : Parcelable