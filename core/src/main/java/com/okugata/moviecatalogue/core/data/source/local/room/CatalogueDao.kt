package com.okugata.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM ${PopularMovieEntity.TABLE_NAME}")
    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMovieEntity>)

    @Query("SELECT * FROM ${MovieDetailEntity.TABLE_NAME} WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM ${MovieDetailEntity.TABLE_NAME} WHERE favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movies: MovieDetailEntity)

    @Update
    fun updateMovieDetail(movie: MovieDetailEntity)



    @Query("SELECT * FROM ${PopularTvShowEntity.TABLE_NAME}")
    fun getPopularTvShows(): Flow<List<PopularTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTvShows(tvShow: List<PopularTvShowEntity>)

    @Query("SELECT * FROM ${TvShowDetailEntity.TABLE_NAME} WHERE id = :id")
    fun getTvShowDetail(id: Int): Flow<TvShowDetailEntity>

    @Query("SELECT * FROM ${TvShowDetailEntity.TABLE_NAME} WHERE favorite = 1")
    fun getFavoriteTvShows():  Flow<List<TvShowDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowDetail(tvShows: TvShowDetailEntity)

    @Update
    fun updateTvShowDetail(tvShow: TvShowDetailEntity)
}