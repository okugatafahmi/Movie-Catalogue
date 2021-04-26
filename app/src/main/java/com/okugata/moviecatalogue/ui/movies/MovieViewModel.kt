package com.okugata.moviecatalogue.ui.movies

import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies() = DataDummy.generateDummyMovies()
}