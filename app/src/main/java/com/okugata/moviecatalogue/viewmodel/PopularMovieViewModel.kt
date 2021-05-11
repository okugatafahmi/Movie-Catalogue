package com.okugata.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.vo.Resource

class PopularMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularMovie(): LiveData<Resource<List<PopularMovieEntity>>> =
        catalogueRepository.getPopularMovies()
}