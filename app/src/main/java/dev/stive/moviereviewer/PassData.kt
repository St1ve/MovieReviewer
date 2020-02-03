package dev.stive.moviereviewer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PassData(val movieName: String, val imgResId: Int) : Parcelable