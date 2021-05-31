package com.okugata.moviecatalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase

class MovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getPopularMovie(): LiveData<Resource<List<Movie>>> =
        catalogueUseCase.getPopularMovies().asLiveData()

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        catalogueUseCase.getFavoriteMovies().asLiveData()
}