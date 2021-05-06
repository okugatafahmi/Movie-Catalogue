package com.okugata.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovie
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse

interface CatalogueDataSource {
    fun getPopularMovie(): LiveData<List<PopularMovie>>
    fun getMovieDetail(id: Int): LiveData<MovieDetailResponse>

    fun getPopularTvShow(): LiveData<List<PopularTvShow>>
    fun getTvShowDetail(id: Int): LiveData<TvShowDetailResponse>
}