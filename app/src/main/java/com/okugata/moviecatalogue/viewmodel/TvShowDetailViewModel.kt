package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.source.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse

class TvShowDetailViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getTvShowDetail(id: Int): LiveData<TvShowDetailResponse> =
        catalogueRepository.getTvShowDetail(id)
}