package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.source.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovie

class PopularMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularMovie(): LiveData<List<PopularMovie>> = catalogueRepository.getPopularMovie()
}