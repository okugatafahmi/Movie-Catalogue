package com.okugata.moviecatalogue.ui.movies

import com.okugata.moviecatalogue.data.Movie
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        movieViewModel = MovieViewModel()
    }

    @Test
    fun getMovies() {
        val movies = movieViewModel.getMovies()
        assertNotNull(movies)
        assertTrue(movies.size >= 10)
        for (movie in movies){
            assertThat(movie, instanceOf(Movie::class.java))
        }
    }
}