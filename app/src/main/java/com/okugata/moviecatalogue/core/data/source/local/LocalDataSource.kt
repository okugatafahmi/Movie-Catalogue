package com.okugata.moviecatalogue.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao)
    }

    fun getPopularMovies(): LiveData<List<PopularMovieEntity>> = catalogueDao.getPopularMovies()
    fun insertPopularMovies(movies: List<PopularMovieEntity>) =
        catalogueDao.insertPopularMovies(movies)

    fun getMovieDetail(id: Int): LiveData<MovieDetailEntity> = catalogueDao.getMovieDetail(id)
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieDetailEntity> = catalogueDao.getFavoriteMovies()
    fun insertMovieDetail(movie: MovieDetailEntity) =
        catalogueDao.insertMovieDetail(movie)

    fun setMovieFavorite(movie: MovieDetailEntity, newState: Boolean) {
        movie.favorite = newState
        catalogueDao.updateMovieDetail(movie)
    }


    fun getPopularTvShows(): LiveData<List<PopularTvShowEntity>> = catalogueDao.getPopularTvShows()
    fun insertPopularTvShows(tvShow: List<PopularTvShowEntity>) =
        catalogueDao.insertPopularTvShows(tvShow)

    fun getTvShowDetail(id: Int): LiveData<TvShowDetailEntity> = catalogueDao.getTvShowDetail(id)
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowDetailEntity> = catalogueDao.getFavoriteTvShows()
    fun insertTvShowDetail(tvShow: TvShowDetailEntity) =
        catalogueDao.insertTvShowDetail(tvShow)

    fun setTvShowFavorite(tvShow: TvShowDetailEntity, newState: Boolean) {
        tvShow.favorite = newState
        catalogueDao.updateTvShowDetail(tvShow)
    }
}