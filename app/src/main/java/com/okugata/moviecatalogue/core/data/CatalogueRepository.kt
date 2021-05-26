package com.okugata.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.okugata.moviecatalogue.core.data.source.local.LocalDataSource
import com.okugata.moviecatalogue.core.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.PopularTvShowEntity
import com.okugata.moviecatalogue.core.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.core.data.source.remote.ApiResponse
import com.okugata.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.core.data.source.remote.response.*
import com.okugata.moviecatalogue.core.utils.AppExecutors
import com.okugata.moviecatalogue.core.vo.Resource
import java.util.*

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(
                    remoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<Resource<List<PopularMovieEntity>>> {
        return object :
            NetworkBoundResource<List<PopularMovieEntity>, PopularMovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PopularMovieEntity>> =
                localDataSource.getPopularMovies()

            override fun shouldFetch(data: List<PopularMovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> =
                remoteDataSource.getPopularMovie()

            override fun saveCallResult(data: PopularMovieResponse?) {
                if (data == null) return
                val movies = LinkedList<PopularMovieEntity>()
                for (response in data.results) {
                    val movie = PopularMovieEntity(
                        response.id,
                        response.title,
                        response.posterPath,
                        response.releaseDate
                    )
                    movies.add(movie)
                }

                localDataSource.insertPopularMovies(movies)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieDetailEntity>> {
        return object : NetworkBoundResource<MovieDetailEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieDetailEntity> =
                localDataSource.getMovieDetail(id)

            override fun shouldFetch(data: MovieDetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override fun saveCallResult(data: MovieDetailResponse?) {
                if (data == null) return
                val movie = MovieDetailEntity(
                    data.id,
                    data.title,
                    data.getGenres(),
                    data.overview,
                    data.runtime,
                    data.posterPath,
                    data.releaseDate
                )

                localDataSource.insertMovieDetail(movie)
            }

        }.asLiveData()
    }

    override fun getPopularTvShows(): LiveData<Resource<List<PopularTvShowEntity>>> {
        return object :
            NetworkBoundResource<List<PopularTvShowEntity>, PopularTvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PopularTvShowEntity>> =
                localDataSource.getPopularTvShows()

            override fun shouldFetch(data: List<PopularTvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularTvShowResponse>> =
                remoteDataSource.getPopularTvShow()

            override fun saveCallResult(data: PopularTvShowResponse?) {
                if (data == null) return
                val tvShows = LinkedList<PopularTvShowEntity>()
                for (response in data.results) {
                    val tvShow = PopularTvShowEntity(
                        response.id,
                        response.name,
                        response.posterPath,
                        response.firstAirDate
                    )
                    tvShows.add(tvShow)
                }

                localDataSource.insertPopularTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(id: Int): LiveData<Resource<TvShowDetailEntity>> {
        return object :
            NetworkBoundResource<TvShowDetailEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowDetailEntity> =
                localDataSource.getTvShowDetail(id)

            override fun shouldFetch(data: TvShowDetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(id)

            override fun saveCallResult(data: TvShowDetailResponse?) {
                if (data == null) return
                val tvShow = TvShowDetailEntity(
                    data.id,
                    data.name,
                    data.numberOfEpisodes,
                    data.getGenres(),
                    data.firstAirDate,
                    data.overview,
                    data.posterPath,
                    data.status
                )

                localDataSource.insertTvShowDetail(tvShow)
            }

        }.asLiveData()


    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setMovieFavorite(movie: MovieDetailEntity, newState: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, newState) }

    override fun setTvShowFavorite(tvShow: TvShowDetailEntity, newState: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, newState) }


}