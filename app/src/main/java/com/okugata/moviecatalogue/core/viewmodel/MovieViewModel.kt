package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.vo.Resource

class MovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getPopularMovie(): LiveData<Resource<List<Movie>>> =
        catalogueUseCase.getPopularMovies()

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        catalogueUseCase.getFavoriteMovies()
}