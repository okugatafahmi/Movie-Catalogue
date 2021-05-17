package com.okugata.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getPopularMovies(): LiveData<Resource<List<PopularMovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieDetailEntity>>

    fun getPopularTvShows(): LiveData<Resource<List<PopularTvShowEntity>>>
    fun getTvShowDetail(id: Int): LiveData<Resource<TvShowDetailEntity>>

    fun getFavoriteMovies(): LiveData<PagingData<MovieDetailEntity>>
    fun getFavoriteTvShows(): LiveData<PagingData<TvShowDetailEntity>>

    fun setMovieFavorite(movie: MovieDetailEntity, newState: Boolean)
    fun setTvShowFavorite(tvShow: TvShowDetailEntity, newState: Boolean)
}