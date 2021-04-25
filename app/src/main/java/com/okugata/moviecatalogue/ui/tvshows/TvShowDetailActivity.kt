package com.okugata.moviecatalogue.ui.tvshows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.data.TvShow
import com.okugata.moviecatalogue.databinding.ActivityTvShowDetailBinding

class TvShowDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TV_SHOW ="extra_tv_show"
    }
    private lateinit var binding: ActivityTvShowDetailBinding
    private lateinit var tvShow: TvShow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTvShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvShow = intent.getParcelableExtra<TvShow>(EXTRA_TV_SHOW) as TvShow

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = tvShow.title
        }

        with(binding) {
            Glide.with(this@TvShowDetailActivity)
                .load(tvShow.img)
                .into(imagePoster)
            textTitle.text = tvShow.title
            textDate.text = tvShow.releaseDate
            textOverview.text = tvShow.overview
            val episode = resources.getQuantityString(R.plurals.numberOfEpisode, tvShow.episode, tvShow.episode)
            textDetail.text = getString(R.string.separator_3, "\u2022",
                tvShow.genre, tvShow.duration, episode)
        }
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
                getString(R.string.share_tv_show,tvShow.title)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}