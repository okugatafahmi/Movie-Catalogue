package com.okugata.moviecatalogue.ui.detail

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
import com.okugata.moviecatalogue.api.ApiConfig.IMAGE_BASE_URL
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.data.source.remote.response.TvShowDetailResponse
import com.okugata.moviecatalogue.databinding.ActivityDetailBinding
import com.okugata.moviecatalogue.utils.DeviceLocale.convertDate
import com.okugata.moviecatalogue.viewmodel.DetailViewModel
import com.okugata.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_IS_MOVIE = "extra_is_movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var isMovie = true
    private var movie: MovieDetailResponse? = null
    private var tvShow: TvShowDetailResponse? = null
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance()
        )[DetailViewModel::class.java]
        id = intent.getIntExtra(EXTRA_ID, 0)
        isMovie = intent.getBooleanExtra(EXTRA_IS_MOVIE, true)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(EXTRA_TITLE)
        }

        setLoading(true)
        if (isMovie) {
            detailViewModel.getMovieDetail(id).observe(this) { movie ->
                setLoading(false)
                movie?.let { setDetail(it) }
            }
        } else {
            detailViewModel.getTvShowDetail(id).observe(this) { tvShow ->
                setLoading(false)
                tvShow?.let { setDetail(it) }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.share -> shareItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareItem() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                if (isMovie) {
                    getString(R.string.share_movie, movie?.title)
                } else {
                    getString(R.string.share_tv_show, tvShow?.name)
                }
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setDetail(movie: MovieDetailResponse) {
        this.movie = movie
        supportActionBar?.title = movie.title
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("${IMAGE_BASE_URL}${movie.posterPath}")
                .placeholder(ColorDrawable(Color.GRAY))
                .into(imagePoster)
            textTitle.text = movie.title
            textDate.text = convertDate(movie.releaseDate)
            textOverview.text = movie.overview
            textDetail.text = getString(
                R.string.separator_2, "\u2022",
                movie.getGenres(), "${movie.runtime} minutes"
            )
        }
    }

    private fun setDetail(tvShow: TvShowDetailResponse) {
        this.tvShow = tvShow
        supportActionBar?.title = tvShow.name
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("${IMAGE_BASE_URL}${tvShow.posterPath}")
                .placeholder(ColorDrawable(Color.GRAY))
                .into(imagePoster)
            textTitle.text = tvShow.name
            textDate.text = convertDate(tvShow.firstAirDate)
            textOverview.text = tvShow.overview
            val episode = resources.getQuantityString(
                R.plurals.numberOfEpisode,
                tvShow.numberOfEpisodes,
                tvShow.numberOfEpisodes
            )
            textDetail.text = getString(
                R.string.separator_3, "\u2022",
                tvShow.getGenres(), episode, tvShow.status
            )
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