package com.okugata.moviecatalogue.api

import com.okugata.moviecatalogue.BuildConfig
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val LANGUAGE = "en-US"
    }

    @GET("movie/popular?api_key=${BuildConfig.MOVIE_DB_TOKEN}")
    fun getPopularMovie(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<PopularMovieResponse>

    @GET("movie/{id}?api_key=${BuildConfig.MOVIE_DB_TOKEN}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): Call<MovieDetailResponse>

    @GET("tv/popular?api_key=${BuildConfig.MOVIE_DB_TOKEN}")
    fun getPopularTvShow(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<PopularTvShowResponse>

    @GET("tv/{id}?api_key=${BuildConfig.MOVIE_DB_TOKEN}")
    fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): Call<TvShowDetailResponse>
}