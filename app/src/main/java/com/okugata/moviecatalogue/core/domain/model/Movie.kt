package com.okugata.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    var genres: String? = null,
    var overview: String? = null,
    var runtime: Int? = null,
    var favorite: Boolean = false
): Parcelable