package com.okugata.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.Movie
import com.okugata.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    var movie: Movie? = null
    fun getMovies() = DataDummy.generateDummyMovies()
}