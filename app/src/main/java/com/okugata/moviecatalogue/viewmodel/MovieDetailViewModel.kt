package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.source.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse

class MovieDetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<MovieDetailResponse> =
        catalogueRepository.getMovieDetail(id)
}