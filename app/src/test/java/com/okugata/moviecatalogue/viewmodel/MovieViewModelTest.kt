package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovie
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
    private lateinit var observer: Observer<List<PopularMovie>>

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel(catalogueRepository)
    }

    @Test
    fun getPopularMovie() {
        val dummyPopularMovie = ArrayList<PopularMovie>()
        val popularMovie = MutableLiveData<List<PopularMovie>>()
        popularMovie.value = dummyPopularMovie

        `when`(catalogueRepository.getPopularMovie()).thenReturn(popularMovie)
        val movies = movieViewModel.getPopularMovie().value
        verify(catalogueRepository).getPopularMovie()
        assertNotNull(movies)

        movieViewModel.getPopularMovie().observeForever(observer)
        verify(observer).onChanged(dummyPopularMovie)
    }
}