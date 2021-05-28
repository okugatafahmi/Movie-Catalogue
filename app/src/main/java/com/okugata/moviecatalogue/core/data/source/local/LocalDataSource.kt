package com.okugata.moviecatalogue.core.data.source.local

import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    fun getPopularMovies(): Flow<List<PopularMovieEntity>> = catalogueDao.getPopularMovies()
    suspend fun insertPopularMovies(movies: List<PopularMovieEntity>) =
        catalogueDao.insertPopularMovies(movies)

    fun getMovieDetail(id: Int): Flow<MovieDetailEntity> = catalogueDao.getMovieDetail(id)
    fun getFavoriteMovies(): Flow<List<MovieDetailEntity>> = catalogueDao.getFavoriteMovies()
    suspend fun insertMovieDetail(movie: MovieDetailEntity) =
        catalogueDao.insertMovieDetail(movie)

    fun setMovieFavorite(movie: MovieDetailEntity, newState: Boolean) {
        movie.favorite = newState
        catalogueDao.updateMovieDetail(movie)
    }


    fun getPopularTvShows(): Flow<List<PopularTvShowEntity>> = catalogueDao.getPopularTvShows()
    suspend fun insertPopularTvShows(tvShow: List<PopularTvShowEntity>) =
        catalogueDao.insertPopularTvShows(tvShow)

    fun getTvShowDetail(id: Int): Flow<TvShowDetailEntity> = catalogueDao.getTvShowDetail(id)
    fun getFavoriteTvShows(): Flow<List<TvShowDetailEntity>> = catalogueDao.getFavoriteTvShows()
    suspend fun insertTvShowDetail(tvShow: TvShowDetailEntity) =
        catalogueDao.insertTvShowDetail(tvShow)

    fun setTvShowFavorite(tvShow: TvShowDetailEntity, newState: Boolean) {
        tvShow.favorite = newState
        catalogueDao.updateTvShowDetail(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(academyDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(academyDao)
    }
}