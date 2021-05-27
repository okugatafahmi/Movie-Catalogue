package com.okugata.moviecatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.vo.Resource

interface ICatalogueRepository {
    fun getPopularMovies(): LiveData<Resource<List<Movie>>>
    fun getMovieDetail(id: Int): LiveData<Resource<Movie>>

    fun getPopularTvShows(): LiveData<Resource<List<TvShow>>>
    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>>

    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun getFavoriteTvShows(): LiveData<List<TvShow>>

    fun setMovieFavorite(movie: Movie, newState: Boolean)
    fun setTvShowFavorite(tvShow: TvShow, newState: Boolean)
}