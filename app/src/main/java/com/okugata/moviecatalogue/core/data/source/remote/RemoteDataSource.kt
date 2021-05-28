package com.okugata.moviecatalogue.core.data.source.remote

import android.util.Log
import com.okugata.moviecatalogue.core.data.source.remote.network.ApiConfig
import com.okugata.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.core.utils.DeviceLocale
import com.okugata.moviecatalogue.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource private constructor() {

    suspend fun getPopularMovie(): Flow<ApiResponse<PopularMovieResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = ApiConfig.getApiService()
                    .getPopularMovie(DeviceLocale.getLanguage())
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(id: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = ApiConfig.getApiService()
                    .getMovieDetail(id, DeviceLocale.getLanguage())
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularTvShow(): Flow<ApiResponse<PopularTvShowResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = ApiConfig.getApiService()
                    .getPopularTvShow(DeviceLocale.getLanguage())
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShowDetail(id: Int): Flow<ApiResponse<TvShowDetailResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            try {
                val response = ApiConfig.getApiService()
                    .getTvShowDetail(id, DeviceLocale.getLanguage())
                emit(ApiResponse.Success(response))
                EspressoIdlingResource.decrement()
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
                EspressoIdlingResource.decrement()
            }

        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        const val TAG = "RemoteDataSource"

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }
}