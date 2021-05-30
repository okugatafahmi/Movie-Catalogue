package com.okugata.moviecatalogue.core.data

import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.data.source.remote.response.*
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.repository.ICatalogueRepository
import com.okugata.moviecatalogue.core.utils.AppExecutors
import com.okugata.moviecatalogue.core.utils.MovieMapper
import com.okugata.moviecatalogue.core.utils.TvShowMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, PopularMovieResponse>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getPopularMovies().map {
                    MovieMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovie()

            override suspend fun saveCallResult(data: PopularMovieResponse) {
                val movies = MovieMapper.mapResponsesToEntities(data.results)
                localDataSource.insertPopularMovies(movies)
            }

        }.asFlow()
    }

    @Suppress("UNNECESSARY_SAFE_CALL")
    override fun getMovieDetail(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<Movie> =
                localDataSource.getMovieDetail(id).map { movie ->
                    movie?.let { MovieMapper.mapEntityToDomain(it) }
                }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val movie = MovieMapper.mapResponseToEntity(data)
                localDataSource.insertMovieDetail(movie)
            }

        }.asFlow()
    }

    override fun getPopularTvShows(): Flow<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, PopularTvShowResponse>() {
            override fun loadFromDB(): Flow<List<TvShow>> =
                localDataSource.getPopularTvShows().map {
                    TvShowMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<PopularTvShowResponse>> =
                remoteDataSource.getPopularTvShow()

            override suspend fun saveCallResult(data: PopularTvShowResponse) {
                val tvShows = TvShowMapper.mapResponsesToEntities(data.results)
                localDataSource.insertPopularTvShows(tvShows)
            }

        }.asFlow()
    }

    @Suppress("UNNECESSARY_SAFE_CALL")
    override fun getTvShowDetail(id: Int): Flow<Resource<TvShow>> {
        return object :
            NetworkBoundResource<TvShow, TvShowDetailResponse>() {
            override fun loadFromDB(): Flow<TvShow> =
                localDataSource.getTvShowDetail(id).map { tvShow ->
                    tvShow?.let { TvShowMapper.mapEntityToDomain(it) }
                }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(id)

            override suspend fun saveCallResult(data: TvShowDetailResponse) {
                val tvShow = TvShowMapper.mapResponseToEntity(data)
                localDataSource.insertTvShowDetail(tvShow)
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map {
            MovieMapper.mapEntitiesToDomain(it)
        }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> =
        localDataSource.getFavoriteTvShows().map {
            TvShowMapper.mapEntitiesToDomain(it)
        }

    override fun setMovieFavorite(movie: Movie, newState: Boolean) {
        val entity = MovieMapper.mapDomainToDetailEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(entity, newState) }
    }

    override fun setTvShowFavorite(tvShow: TvShow, newState: Boolean) {
        val entity = TvShowMapper.mapDomainToDetailEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(entity, newState) }
    }
}