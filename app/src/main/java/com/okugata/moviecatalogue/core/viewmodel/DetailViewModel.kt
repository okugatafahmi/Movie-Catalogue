package com.okugata.moviecatalogue.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.vo.Resource

class DetailViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    fun getMovieDetail(id: Int): LiveData<Resource<Movie>> =
        catalogueUseCase.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<Resource<TvShow>> =
        catalogueUseCase.getTvShowDetail(id)

    fun setMovieFavorite(movie: Movie) =
        catalogueUseCase.setMovieFavorite(movie, !movie.favorite)

    fun setTvShowFavorite(tvShow: TvShow) =
        catalogueUseCase.setTvShowFavorite(tvShow, !tvShow.favorite)
}