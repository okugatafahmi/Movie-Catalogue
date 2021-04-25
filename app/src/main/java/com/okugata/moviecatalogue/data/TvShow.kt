package com.okugata.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val title: String,
    val releaseDate: String,
    val img: Int,
    val overview: String,
    val genre: String,
    val duration: String,
    val episode: Int
) : Parcelable
