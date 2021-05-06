package com.okugata.moviecatalogue.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.okugata.moviecatalogue.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovie
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CatalogueDataSource {

    companion object {
        const val TAG = "CatalogueRepository"

        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovie(): LiveData<List<PopularMovie>> {
        val popularMovie = MutableLiveData<List<PopularMovie>>()
        remoteDataSource.getPopularMovie { failureMessage, popularMovieResponse ->
            if (failureMessage != null) {
                Log.e(TAG, failureMessage)
                popularMovie.postValue(null)
            }
            else {
                popularMovie.postValue(popularMovieResponse?.results)
            }
        }
        return popularMovie
    }

    override fun getMovieDetail(id: Int): LiveData<MovieDetailResponse> {
        val movieDetail = MutableLiveData<MovieDetailResponse>()
        remoteDataSource.getMovieDetail(id) { failureMessage, movieDetailResponse ->
            if (failureMessage != null) {
                Log.e(TAG, failureMessage)
                movieDetail.postValue(null)
            }
            else {
                movieDetail.postValue(movieDetailResponse)
            }
        }
        return movieDetail
    }

    override fun getPopularTvShow(): LiveData<List<PopularTvShow>> {
        val popularTvShow = MutableLiveData<List<PopularTvShow>>()
        remoteDataSource.getPopularTvShow { failureMessage, popularTvShowResponse ->
            if (failureMessage != null) {
                Log.e(TAG, failureMessage)
                popularTvShow.postValue(null)
            }
            else {
                popularTvShow.postValue(popularTvShowResponse?.results)
            }
        }
        return popularTvShow
    }

    override fun getTvShowDetail(id: Int): LiveData<TvShowDetailResponse> {
        val tvShowDetail = MutableLiveData<TvShowDetailResponse>()
        remoteDataSource.getTvShowDetail(id) { failureMessage, tvShowDetailResponse ->
            if (failureMessage != null) {
                Log.e(TAG, failureMessage)
                tvShowDetail.postValue(null)
            }
            else {
                tvShowDetail.postValue(tvShowDetailResponse)
            }
        }
        return tvShowDetail
    }

}