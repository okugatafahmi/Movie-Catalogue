package com.okugata.moviecatalogue.api

import com.okugata.moviecatalogue.data.MovieDetailResponse
import com.okugata.moviecatalogue.data.PopularMovieResponse
import com.okugata.moviecatalogue.data.PopularTvShowResponse
import com.okugata.moviecatalogue.data.TvShowDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = "43a9a82697cc1d58c5e172dd226721c7"
        const val LANGUAGE = "en-US"
    }

    @GET("movie/popular?api_key=$API_KEY")
    fun getPopularMovie(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<PopularMovieResponse>

    @GET("movie/{id}?api_key=$API_KEY")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): Call<MovieDetailResponse>

    @GET("tv/popular?api_key=$API_KEY")
    fun getPopularTvShow(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Call<PopularTvShowResponse>

    @GET("tv/{id}?api_key=$API_KEY")
    fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): Call<TvShowDetailResponse>
}