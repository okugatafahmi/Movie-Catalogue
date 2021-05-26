package com.okugata.moviecatalogue.core.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.core.data.CatalogueRepository
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.vo.Resource
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
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observerPopular: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var observerFavorite: Observer<List<Movie>>

    private lateinit var movieViewModel: MovieViewModel
    @Mock
    private lateinit var movies: List<Movie>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getPopularMovie() {
        val dummyPopularMovie: Resource<List<Movie>> = Resource.success(ArrayList())
        val popularMovie = MutableLiveData<Resource<List<Movie>>>()
        popularMovie.value = dummyPopularMovie

        `when`(catalogueRepository.getPopularMovies()).thenReturn(popularMovie)
        val movies = movieViewModel.getPopularMovie().value
        verify(catalogueRepository).getPopularMovies()
        assertNotNull(movies)

        movieViewModel.getPopularMovie().observeForever(observerPopular)
        verify(observerPopular).onChanged(dummyPopularMovie)
    }

    @Test
    fun getFavoriteMovie() {
        val favoriteMovie = MutableLiveData<List<Movie>>()
        favoriteMovie.value = movies

        `when`(catalogueRepository.getFavoriteMovies()).thenReturn(favoriteMovie)
        val movies = movieViewModel.getFavoriteMovie().value
        verify(catalogueRepository).getFavoriteMovies()
        assertNotNull(movies)

        movieViewModel.getFavoriteMovie().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(this.movies)
    }
}