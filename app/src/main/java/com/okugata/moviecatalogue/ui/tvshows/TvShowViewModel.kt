package com.okugata.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShows() = DataDummy.generateDummyTvShows()
}