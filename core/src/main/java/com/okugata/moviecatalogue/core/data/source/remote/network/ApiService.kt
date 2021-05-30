package com.okugata.moviecatalogue.core.data.source.remote.network

import com.okugata.moviecatalogue.core.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.core.data.source.remote.response.TvShowDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val LANGUAGE = "en-US"
        const val MOVIE_DB_TOKEN = "43a9a82697cc1d58c5e172dd226721c7"
        fun getImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
    }

    @GET("movie/popular?api_key=${MOVIE_DB_TOKEN}")
    suspend fun getPopularMovie(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): PopularMovieResponse

    @GET("movie/{id}?api_key=${MOVIE_DB_TOKEN}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): MovieDetailResponse

    @GET("tv/popular?api_key=${MOVIE_DB_TOKEN}")
    suspend fun getPopularTvShow(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): PopularTvShowResponse

    @GET("tv/{id}?api_key=${MOVIE_DB_TOKEN}")
    suspend fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("language") language: String = LANGUAGE
    ): TvShowDetailResponse
}