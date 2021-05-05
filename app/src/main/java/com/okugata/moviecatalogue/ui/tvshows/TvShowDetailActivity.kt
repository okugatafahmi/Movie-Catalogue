package com.okugata.moviecatalogue.ui.tvshows

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.TvShowDetailResponse
import com.okugata.moviecatalogue.databinding.ActivityTvShowDetailBinding

class TvShowDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW_ID ="extra_tv_show_id"
        const val EXTRA_TV_SHOW_TITLE ="extra_tv_show_title"
    }

    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var tvShowDetailViewModel: TvShowDetailViewModel
    private var tvShowId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvShowDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[TvShowDetailViewModel::class.java]
        tvShowId = intent.getIntExtra(EXTRA_TV_SHOW_ID, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(EXTRA_TV_SHOW_TITLE)
        }

        tvShowDetailViewModel.tvShowDetail.observe(this) { setDetail(it) }
        tvShowDetailViewModel.isLoading.observe(this) { setLoading(it) }
        tvShowDetailViewModel.getTvShowDetail(tvShowId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            R.id.share -> shareTvShow()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareTvShow() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.share_tv_show,tvShowDetailViewModel.tvShowDetail.value?.name)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setDetail(tvShow: TvShowDetailResponse) {
        supportActionBar?.title = tvShow.name
        with(binding) {
            Glide.with(this@TvShowDetailActivity)
                .load("${ApiConfig.IMAGE_BASE_URL}${tvShow.posterPath}")
                .placeholder(ColorDrawable(Color.GRAY))
                .into(imagePoster)
            textTitle.text = tvShow.name
            textDate.text = tvShow.firstAirDate
            textOverview.text = tvShow.overview
            val episode = resources.getQuantityString(R.plurals.numberOfEpisode, tvShow.numberOfEpisodes, tvShow.numberOfEpisodes)
            textDetail.text = getString(R.string.separator_3, "\u2022",
                tvShow.getGenres(), episode, tvShow.status)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        val progressBarVisibility = if (isLoading) View.VISIBLE else View.GONE
        val othersVisibility = if (isLoading) View.GONE else View.VISIBLE
        with(binding) {
            progressBar.visibility = progressBarVisibility
            headerOverview.visibility = othersVisibility
        }
    }
}