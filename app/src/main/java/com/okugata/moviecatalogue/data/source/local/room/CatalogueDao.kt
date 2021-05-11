package com.okugata.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM ${PopularMovieEntity.TABLE_NAME}")
    fun getPopularMovies(): LiveData<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movies: List<PopularMovieEntity>)

    @Query("SELECT * FROM ${MovieDetailEntity.TABLE_NAME} WHERE id = :id")
    fun getMovieDetail(id: Int): LiveData<MovieDetailEntity>

    @Query("SELECT * FROM ${MovieDetailEntity.TABLE_NAME} WHERE favorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movies: MovieDetailEntity)

    @Update
    fun updateMovieDetail(movie: MovieDetailEntity)



    @Query("SELECT * FROM ${PopularTvShowEntity.TABLE_NAME}")
    fun getPopularTvShows(): LiveData<List<PopularTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularTvShows(tvShow: List<PopularTvShowEntity>)

    @Query("SELECT * FROM ${TvShowDetailEntity.TABLE_NAME} WHERE id = :id")
    fun getTvShowDetail(id: Int): LiveData<TvShowDetailEntity>

    @Query("SELECT * FROM ${TvShowDetailEntity.TABLE_NAME} WHERE favorite = 1")
    fun getFavoriteTvShows(): LiveData<List<TvShowDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetail(tvShows: TvShowDetailEntity)

    @Update
    fun updateTvShowDetail(tvShow: TvShowDetailEntity)
}