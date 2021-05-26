package com.okugata.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.remote.ApiResponse
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.utils.AppExecutors
import com.okugata.moviecatalogue.core.utils.MovieMapper
import com.okugata.moviecatalogue.core.utils.TvShowMapper
import com.okugata.moviecatalogue.core.vo.Resource

class FakeCatalogueRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, PopularMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> =
                Transformations.map(localDataSource.getPopularMovies()) {
                    MovieMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: PopularMovieResponse?) {
                if (data == null) return
                val movies = MovieMapper.mapResponsesToEntities(data.results)

                localDataSource.insertPopularMovies(movies)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<Movie> =
                Transformations.map(localDataSource.getMovieDetail(id)) {
                    MovieMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override fun saveCallResult(data: MovieDetailResponse?) {
                if (data == null) return
                val movie = MovieMapper.mapResponseToEntity(data)

                localDataSource.insertMovieDetail(movie)
            }

        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<TvShow>>> {
        return object :
            NetworkBoundResource<List<TvShow>, PopularTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShow>> =
                Transformations.map(localDataSource.getPopularTvShows()) {
                    TvShowMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularTvShowResponse>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: PopularTvShowResponse?) {
                if (data == null) return
                val tvShows = TvShowMapper.mapResponsesToEntities(data.results)

                localDataSource.insertPopularTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> {
        return object :
            NetworkBoundResource<TvShow, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShow> =
                Transformations.map(localDataSource.getTvShowDetail(id)) {
                    TvShowMapper.mapEntityToDomain(it)
                }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(id)

            override fun saveCallResult(data: TvShowDetailResponse?) {
                if (data == null) return
                val tvShow = TvShowMapper.mapResponseToEntity(data)

                localDataSource.insertTvShowDetail(tvShow)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> =
        Transformations.map(localDataSource.getFavoriteMovies()) {
            MovieMapper.mapEntitiesToDomain(it)
        }

    override fun getFavoriteTvShows(): LiveData<List<TvShow>> =
        Transformations.map(localDataSource.getFavoriteTvShows()) {
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