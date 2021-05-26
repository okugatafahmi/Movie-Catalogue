package com.okugata.moviecatalogue.core.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okugata.moviecatalogue.core.api.ApiConfig
import com.okugata.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.core.utils.DeviceLocale
import com.okugata.moviecatalogue.core.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor() {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getPopularMovie(): LiveData<ApiResponse<PopularMovieResponse>> {
        val popularMovie = MutableLiveData<ApiResponse<PopularMovieResponse>>()

        val client = ApiConfig.getApiService()
            .getPopularMovie(DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        popularMovie.value = ApiResponse.success(response.body())
                    } else {
                        popularMovie.value = ApiResponse.empty(response.message(), response.body())
                    }
                } else {
                    popularMovie.value = ApiResponse.error(response.message(), null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                popularMovie.value = ApiResponse.error(t.message.toString(), null)
                EspressoIdlingResource.decrement()
            }

        })

        return popularMovie
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieDetailResponse>> {
         val movieDetail = MutableLiveData<ApiResponse<MovieDetailResponse>>()

        val client = ApiConfig.getApiService()
            .getMovieDetail(id, DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        movieDetail.value = ApiResponse.success(response.body())
                    } else {
                        movieDetail.value = ApiResponse.empty(response.message(), response.body())
                    }
                } else {
                    movieDetail.value = ApiResponse.error(response.message(), null)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                movieDetail.value = ApiResponse.error(t.message.toString(), null)
                EspressoIdlingResource.decrement()
            }

        })

        return movieDetail
    }

    fun getPopularTvShow(): LiveData<ApiResponse<PopularTvShowResponse>> {
        val popularTvShow = MutableLiveData<ApiResponse<PopularTvShowResponse>>()

        val client = ApiConfig.getApiService()
            .getPopularTvShow(DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        popularTvShow.value = ApiResponse.success(response.body())
                    } else {
                        popularTvShow.value = ApiResponse.empty(response.message(), response.body())
                    }
                } else {
                    popularTvShow.value = ApiResponse.error(response.message(), null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                popularTvShow.value = ApiResponse.error(t.message.toString(), null)
                EspressoIdlingResource.decrement()
            }

        })

        return popularTvShow
    }

    fun getTvShowDetail(id: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        val tvShowDetail = MutableLiveData<ApiResponse<TvShowDetailResponse>>()

        val client = ApiConfig.getApiService()
            .getTvShowDetail(id, DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        tvShowDetail.value = ApiResponse.success(response.body())
                    } else {
                        tvShowDetail.value = ApiResponse.empty(response.message(), response.body())
                    }
                } else {
                    tvShowDetail.value = ApiResponse.error(response.message(), null)
                }

                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                tvShowDetail.value = ApiResponse.error(t.message.toString(), null)
                EspressoIdlingResource.decrement()
            }

        })

        return tvShowDetail
    }
}