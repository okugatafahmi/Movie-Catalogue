package com.okugata.moviecatalogue.core.utils

import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.core.domain.model.TvShow

object TvShowMapper {
    fun mapResponsesToEntities(input: List<PopularTvShow>): List<PopularTvShowEntity> =
        input.map {
            PopularTvShowEntity(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
            )
        }

    fun mapResponseToEntity(input: TvShowDetailResponse): TvShowDetailEntity =
        TvShowDetailEntity(
            id = input.id,
            name = input.name,
            posterPath = input.posterPath,
            firstAirDate = input.firstAirDate,
            genres = input.getGenres(),
            overview = input.overview,
            numberOfEpisodes = input.numberOfEpisodes,
            status = input.status,
        )

    fun mapEntitiesToDomain(input: List<PopularTvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
            )
        }

    @JvmName("mapEntitiesToDomain1")
    fun mapEntitiesToDomain(input: List<TvShowDetailEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                firstAirDate = it.firstAirDate,
                genres = it.genres,
                overview = it.overview,
                numberOfEpisodes = it.numberOfEpisodes,
                status = it.status,
                favorite = it.favorite
            )
        }

    fun mapEntityToDomain(input: TvShowDetailEntity): TvShow =
        TvShow(
            id = input.id,
            name = input.name,
            posterPath = input.posterPath,
            firstAirDate = input.firstAirDate,
            genres = input.genres,
            overview = input.overview,
            numberOfEpisodes = input.numberOfEpisodes,
            status = input.status,
            favorite = input.favorite
        )

    fun mapDomainToDetailEntity(input: TvShow): TvShowDetailEntity =
        TvShowDetailEntity(
            id = input.id,
            name = input.name,
            posterPath = input.posterPath,
            firstAirDate = input.firstAirDate,
            genres = input.genres ?: "",
            overview = input.overview ?: "",
            numberOfEpisodes = input.numberOfEpisodes ?: 0,
            status = input.status ?: "",
            favorite = input.favorite
        )
}