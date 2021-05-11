package com.okugata.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getPopularMovies(): LiveData<Resource<List<PopularMovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieDetailEntity>>

    fun getPopularTvShows(): LiveData<Resource<List<PopularTvShowEntity>>>
    fun getTvShowDetail(id: Int): LiveData<Resource<TvShowDetailEntity>>

    fun getFavoriteMovies(): LiveData<List<MovieDetailEntity>>
    fun getFavoriteTvShows(): LiveData<List<TvShowDetailEntity>>

    fun setMovieFavorite(movie: MovieDetailEntity, newState: Boolean)
    fun setTvShowFavorite(tvShow: TvShowDetailEntity, newState: Boolean)
}