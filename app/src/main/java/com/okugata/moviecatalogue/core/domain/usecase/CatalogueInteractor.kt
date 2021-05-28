package com.okugata.moviecatalogue.core.domain.usecase

import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class CatalogueInteractor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        catalogueRepository.getPopularMovies()

    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> =
        catalogueRepository.getMovieDetail(id)

    override fun getPopularTvShows(): Flow<Resource<List<TvShow>>> =
        catalogueRepository.getPopularTvShows()

    override fun getTvShowDetail(id: Int): Flow<Resource<TvShow>> =
        catalogueRepository.getTvShowDetail(id)

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        catalogueRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): Flow<List<TvShow>> =
        catalogueRepository.getFavoriteTvShows()

    override fun setMovieFavorite(movie: Movie, newState: Boolean) =
        catalogueRepository.setMovieFavorite(movie, newState)

    override fun setTvShowFavorite(tvShow: TvShow, newState: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, newState)
}