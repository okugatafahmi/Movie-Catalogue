package com.okugata.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.okugata.moviecatalogue.data.CatalogueRepository
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvShowDetailResponse>

    @Mock
    private lateinit var dummyTvShowDetail: TvShowDetailResponse

    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel
    private var id = 1

    @Before
    fun setUp() {
        tvShowDetailViewModel = TvShowDetailViewModel(catalogueRepository)
    }

    @Test
    fun getTvShowDetail() {
        val tvShowDetail = MutableLiveData<TvShowDetailResponse>()
        tvShowDetail.value = dummyTvShowDetail

        Mockito.`when`(catalogueRepository.getTvShowDetail(id)).thenReturn(tvShowDetail)
        val tvShow = tvShowDetailViewModel.getTvShowDetail(id).value
        Mockito.verify(catalogueRepository).getTvShowDetail(id)
        assertNotNull(tvShow)

        tvShowDetailViewModel.getTvShowDetail(id).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShowDetail)
    }
}