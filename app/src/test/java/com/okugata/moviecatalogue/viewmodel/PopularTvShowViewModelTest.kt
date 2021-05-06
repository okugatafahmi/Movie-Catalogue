package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularTvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<PopularTvShow>>

    private lateinit var popularTvShowViewModel: PopularTvShowViewModel

    @Before
    fun setUp() {
        popularTvShowViewModel = PopularTvShowViewModel(catalogueRepository)
    }

    @Test
    fun getPopularTvShow() {
        val dummyPopularTvShow = ArrayList<PopularTvShow>()
        val popularTvShow = MutableLiveData<List<PopularTvShow>>()
        popularTvShow.value = dummyPopularTvShow

        Mockito.`when`(catalogueRepository.getPopularTvShow()).thenReturn(popularTvShow)
        val movies = popularTvShowViewModel.getPopularTvShow().value
        verify(catalogueRepository).getPopularTvShow()
        assertNotNull(movies)

        popularTvShowViewModel.getPopularTvShow().observeForever(observer)
        verify(observer).onChanged(dummyPopularTvShow)
    }
}