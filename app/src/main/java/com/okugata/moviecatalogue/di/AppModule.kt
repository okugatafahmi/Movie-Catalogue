package com.okugata.moviecatalogue.di

import com.okugata.moviecatalogue.core.domain.usecase.CatalogueInteractor
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import com.okugata.moviecatalogue.core.viewmodel.DetailViewModel
import com.okugata.moviecatalogue.core.viewmodel.MovieViewModel
import com.okugata.moviecatalogue.core.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueUseCase> { CatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}