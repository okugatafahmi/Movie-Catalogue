package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.PopularMovieEntity
import com.okugata.moviecatalogue.vo.Resource
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
    private lateinit var observerPopular: Observer<Resource<List<PopularMovieEntity>>>

    @Mock
    private lateinit var observerFavorite: Observer<List<MovieDetailEntity>>

    private lateinit var movieViewModel: MovieViewModel
    @Mock
    private lateinit var pagedList: PagedList<MovieDetailEntity>

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getPopularMovie() {
        val dummyPopularMovie: Resource<List<PopularMovieEntity>> = Resource.success(ArrayList())
        val popularMovie = MutableLiveData<Resource<List<PopularMovieEntity>>>()
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
        val favoriteMovie = MutableLiveData<PagedList<MovieDetailEntity>>()
        favoriteMovie.value = pagedList

        `when`(catalogueRepository.getFavoriteMovies()).thenReturn(favoriteMovie)
        val movies = movieViewModel.getFavoriteMovie().value
        verify(catalogueRepository).getFavoriteMovies()
        assertNotNull(movies)

        movieViewModel.getFavoriteMovie().observeForever(observerFavorite)
        verify(observerFavorite).onChanged(pagedList)
    }
}