package com.okugata.moviecatalogue.data.source.remote

import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.utils.DeviceLocale
import com.okugata.moviecatalogue.utils.EspressoIdlingResource
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

    fun getPopularMovie(callback: (String?, PopularMovieResponse?) -> Unit) {
        val client = ApiConfig.getApiService()
            .getPopularMovie(DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if (response.isSuccessful) {
                    callback(null, response.body())
                } else {
                    callback("onFailure: ${response.message()}", null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                callback("onFailure: ${t.message.toString()}", null)
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getMovieDetail(id: Int, callback: (String?, MovieDetailResponse?) -> Unit) {
        val client = ApiConfig.getApiService()
            .getMovieDetail(id, DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                if (response.isSuccessful) {
                    callback(null, response.body())
                } else {
                    callback("onFailure: ${response.message()}", null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                callback("onFailure: ${t.message.toString()}", null)
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getPopularTvShow(callback: (String?, PopularTvShowResponse?) -> Unit) {
        val client = ApiConfig.getApiService()
            .getPopularTvShow(DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                if (response.isSuccessful) {
                    callback(null, response.body())
                } else {
                    callback("onFailure: ${response.message()}", null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                callback("onFailure: ${t.message.toString()}", null)
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getTvShowDetail(id: Int, callback: (String?, TvShowDetailResponse?) -> Unit) {
        val client = ApiConfig.getApiService()
            .getTvShowDetail(id, DeviceLocale.getLanguage())
        EspressoIdlingResource.increment()
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                if (response.isSuccessful) {
                    callback(null, response.body())
                } else {
                    callback("onFailure: ${response.message()}", null)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                callback("onFailure: ${t.message.toString()}", null)
                EspressoIdlingResource.decrement()
            }

        })
    }
}