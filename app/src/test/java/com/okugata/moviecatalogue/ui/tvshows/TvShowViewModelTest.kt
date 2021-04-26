package com.okugata.moviecatalogue.ui.tvshows

import com.okugata.moviecatalogue.data.TvShow
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel

    @Before
    fun setUp() {
        tvShowViewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShows = tvShowViewModel.getTvShows()
        assertNotNull(tvShows)
        assertTrue(tvShows.size >= 10)
        for (tvShow in tvShows){
            assertThat(tvShow, CoreMatchers.instanceOf(TvShow::class.java))
        }
    }
}