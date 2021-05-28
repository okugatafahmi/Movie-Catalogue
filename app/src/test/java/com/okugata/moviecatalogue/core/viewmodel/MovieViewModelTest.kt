package com.okugata.moviecatalogue.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.usecase.CatalogueUseCase
import kotlinx.coroutines.flow.flow
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueUseCase: CatalogueUseCase

    @Mock
    private lateinit var observerPopular: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var observerFavorite: Observer<List<Movie>>

    private lateinit var movieViewModel: MovieViewModel
    @Mock
    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueUseCase)
    }

    @Test
    fun getPopularMovie() {
        val dummyPopularMovie: Resource<List<Movie>> = Resource.Success(ArrayList())
        val popularMovie = flow {
            emit(dummyPopularMovie)
        }

        `when`(catalogueUseCase.getPopularMovies()).thenReturn(popularMovie)
        val movies = movieViewModel.getPopularMovie().value
        verify(catalogueUseCase).getPopularMovies()
        assertNotNull(movies)

        movieViewModel.getPopularMovie().observeForever(observerPopular)
        verify(observerPopular).onChanged(dummyPopularMovie)
    }

    @Test
    fun getFavoriteMovie() {
        val favoriteMovie = flow {
            emit(movies)
        }

        `when`(catalogueUseCase.getFavoriteMovies()).thenReturn(favoriteMovie)
        val movies = movieViewModel.getFavoriteMovie().value
        verify(catalogueUseCase).getFavoriteMovies()
        assertNotNull(movies)

        movieViewModel.getFavoriteMovie().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(this.movies)
    }
}