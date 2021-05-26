package com.okugata.moviecatalogue.core.utils

import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularMovie
import com.okugata.moviecatalogue.core.domain.model.Movie

object MovieMapper {

    fun mapResponsesToEntities(input: List<PopularMovie>): List<PopularMovieEntity> =
        input.map {
            PopularMovieEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
            )
        }

    fun mapResponseToEntity(input: MovieDetailResponse): MovieDetailEntity =
        MovieDetailEntity(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genres = input.getGenres(),
            overview = input.overview,
            runtime = input.runtime
        )

    fun mapEntitiesToDomain(input: List<PopularMovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
            )
        }

    @JvmName("mapEntitiesToDomain1")
    fun mapEntitiesToDomain(input: List<MovieDetailEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                genres = it.genres,
                overview = it.overview,
                runtime = it.runtime,
                favorite = it.favorite
            )
        }

    fun mapEntityToDomain(input: MovieDetailEntity): Movie =
        Movie(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genres = input.genres,
            overview = input.overview,
            runtime = input.runtime,
            favorite = input.favorite
        )

    fun mapDomainToDetailEntity(input: Movie): MovieDetailEntity =
        MovieDetailEntity(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genres = input.genres ?: "",
            overview = input.overview ?: "",
            runtime = input.runtime ?: 0,
            favorite = input.favorite
        )
}