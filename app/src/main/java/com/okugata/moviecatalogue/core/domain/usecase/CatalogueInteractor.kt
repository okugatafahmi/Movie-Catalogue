package com.okugata.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.repository.ICatalogueRepository
import com.okugata.moviecatalogue.core.vo.Resource

class CatalogueInteractor(private val catalogueRepository: ICatalogueRepository) :
    CatalogueUseCase {
    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        catalogueRepository.getPopularMovies()

    override fun getMovieDetail(id: Int): LiveData<Resource<Movie>> =
        catalogueRepository.getMovieDetail(id)

    override fun getPopularTvShows(): LiveData<Resource<List<TvShow>>> =
        catalogueRepository.getPopularTvShows()

    override fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> =
        catalogueRepository.getTvShowDetail(id)

    override fun getFavoriteMovies(): LiveData<List<Movie>> =
        catalogueRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): LiveData<List<TvShow>> =
        catalogueRepository.getFavoriteTvShows()

    override fun setMovieFavorite(movie: Movie, newState: Boolean) =
        catalogueRepository.setMovieFavorite(movie, newState)

    override fun setTvShowFavorite(tvShow: TvShow, newState: Boolean) =
        catalogueRepository.setTvShowFavorite(tvShow, newState)
}