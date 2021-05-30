package com.okugata.moviecatalogue.core.domain.usecase

import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(id: Int): Flow<Resource<Movie>>

    fun getPopularTvShows(): Flow<Resource<List<TvShow>>>
    fun getTvShowDetail(id: Int): Flow<Resource<TvShow>>

    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteTvShows(): Flow<List<TvShow>>

    fun setMovieFavorite(movie: Movie, newState: Boolean)
    fun setTvShowFavorite(tvShow: TvShow, newState: Boolean)
}