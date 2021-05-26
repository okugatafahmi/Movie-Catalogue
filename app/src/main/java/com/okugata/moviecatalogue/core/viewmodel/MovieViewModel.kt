package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.vo.Resource

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getPopularMovie(): LiveData<Resource<List<Movie>>> =
        catalogueRepository.getPopularMovies()

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        catalogueRepository.getFavoriteMovies()
}