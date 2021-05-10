package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse

class DetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<MovieDetailResponse> =
        catalogueRepository.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<TvShowDetailResponse> =
        catalogueRepository.getTvShowDetail(id)
}