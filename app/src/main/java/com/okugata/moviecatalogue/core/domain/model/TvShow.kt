package com.okugata.moviecatalogue.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    val firstAirDate: String,
    val posterPath: String,
    var numberOfEpisodes: Int? = null,
    var genres: String? = null,
    var overview: String? = null,
    var status: String? = null,
    var favorite: Boolean = false
): Parcelable
