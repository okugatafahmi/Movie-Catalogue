package com.okugata.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.data.TvShow
import com.okugata.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    var tvShow: TvShow? = null
    fun getTvShows() = DataDummy.generateDummyTvShows()
}