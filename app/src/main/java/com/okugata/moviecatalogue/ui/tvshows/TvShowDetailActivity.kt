package com.okugata.moviecatalogue.ui.tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
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
            textTitle.text = tvShow.title
            textDate.text = tvShow.releaseDate
            textOverview.text = tvShow.overview
            Glide.with(this@TvShowDetailActivity)
                .load(tvShow.img)
                .into(imagePoster)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}