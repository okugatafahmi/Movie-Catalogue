package com.okugata.moviecatalogue.core.utils

import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity

object EntityMock {

    fun popularMovie(): PopularMovieEntity =
        PopularMovieEntity(
            1,
            "Lorem",
            "Lorem",
            "Lorem"
        )

    fun movieDetail(): MovieDetailEntity =
        MovieDetailEntity(
            1,
            "Lorem",
            "Lorem",
            "Lorem",
            1,
            "Lorem",
            "Lorem",
            false
        )

    fun popularTvShow(): PopularTvShowEntity =
        PopularTvShowEntity(
            1,
            "Lorem",
            "Lorem",
            "Lorem"
        )

    fun tvShowDetail(): TvShowDetailEntity =
        TvShowDetailEntity(
            1,
            "Lorem",
            1,
            "Lorem",
            "Lorem",
            "Lorem",
            "Lorem",
            "Lorem",
            false
        )
}