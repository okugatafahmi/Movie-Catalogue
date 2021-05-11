package com.okugata.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.okugata.moviecatalogue.data.source.remote.RemoteDataSource
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularMovieResponse
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShowResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remote: RemoteDataSource
    private lateinit var repository: FakeCatalogueRepository

    @Mock
    private lateinit var popularMovieResponse: PopularMovieResponse
    @Mock
    private lateinit var movieDetailResponse: MovieDetailResponse
    @Mock
    private lateinit var popularTvShowResponse: PopularTvShowResponse
    @Mock
    private lateinit var tvShowDetailResponse: TvShowDetailResponse
    private val id = 1

    @Before
    fun setUp() {
        repository = FakeCatalogueRepository(remote)
    }

//    @Test
//    fun getPopularMovie() {
//        doAnswer {
//            @Suppress("UNCHECKED_CAST")
//            (it.arguments[0] as ((String?, PopularMovieResponse?) -> Unit))
//                .invoke(null, popularMovieResponse)
//            null
//        }.`when`(remote).getPopularMovie(any())
//
//        val movies = LiveDataTestUtil.getValue(repository.getPopularMovie())
//        verify(remote).getPopularMovie(any())
//        assertNotNull(movies)
//        assertEquals(popularMovieResponse.results, movies)
//    }
//
//    @Test
//    fun getMovieDetail() {
//        doAnswer {
//            @Suppress("UNCHECKED_CAST")
//            (it.arguments[1] as ((String?, MovieDetailResponse?) -> Unit))
//                .invoke(null, movieDetailResponse)
//            null
//        }.`when`(remote).getMovieDetail(eq(id),any())
//
//        val movie = LiveDataTestUtil.getValue(repository.getMovieDetail(id))
//        verify(remote).getMovieDetail(eq(id), any())
//        assertNotNull(movie)
//        assertEquals(movieDetailResponse, movie)
//    }
//
//    @Test
//    fun getPopularTvShow() {
//        doAnswer {
//            @Suppress("UNCHECKED_CAST")
//            (it.arguments[0] as ((String?, PopularTvShowResponse?) -> Unit))
//                .invoke(null, popularTvShowResponse)
//            null
//        }.`when`(remote).getPopularTvShow(any())
//
//        val tvShow = LiveDataTestUtil.getValue(repository.getPopularTvShow())
//        verify(remote).getPopularTvShow(any())
//        assertNotNull(tvShow)
//        assertEquals(popularTvShowResponse.results, tvShow)
//    }
//
//    @Test
//    fun getTvShowDetail() {
//        doAnswer {
//            @Suppress("UNCHECKED_CAST")
//            (it.arguments[1] as ((String?, TvShowDetailResponse?) -> Unit))
//                .invoke(null, tvShowDetailResponse)
//            null
//        }.`when`(remote).getTvShowDetail(eq(id),any())
//
//        val tvShow = LiveDataTestUtil.getValue(repository.getTvShowDetail(id))
//        verify(remote).getTvShowDetail(eq(id), any())
//        assertNotNull(tvShow)
//        assertEquals(tvShowDetailResponse, tvShow)
//    }
}