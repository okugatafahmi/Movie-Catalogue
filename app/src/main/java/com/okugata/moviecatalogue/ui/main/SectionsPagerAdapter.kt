package com.okugata.moviecatalogue.ui.main

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.ui.movies.MoviesFragment
import com.okugata.moviecatalogue.ui.tvshows.TvShowsFragment

class SectionsPagerAdapter(
    private val mContext: Context,
    fm: FragmentManager,
    private val isFavorite: Boolean = false
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment(isFavorite)
            1 -> TvShowsFragment(isFavorite)
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}