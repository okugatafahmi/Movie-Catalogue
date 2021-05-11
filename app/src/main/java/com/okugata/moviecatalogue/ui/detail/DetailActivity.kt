package com.okugata.moviecatalogue.ui.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.api.ApiConfig.IMAGE_BASE_URL
import com.okugata.moviecatalogue.data.source.local.entity.MovieDetailEntity
import com.okugata.moviecatalogue.data.source.local.entity.TvShowDetailEntity
import com.okugata.moviecatalogue.databinding.ActivityDetailBinding
import com.okugata.moviecatalogue.utils.DeviceLocale.convertDate
import com.okugata.moviecatalogue.viewmodel.DetailViewModel
import com.okugata.moviecatalogue.viewmodel.ViewModelFactory
import com.okugata.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_IS_MOVIE = "extra_is_movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private var isMovie = true
    private var movie: MovieDetailEntity? = null
    private var tvShow: TvShowDetailEntity? = null
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[DetailViewModel::class.java]
        id = intent.getIntExtra(EXTRA_ID, 0)
        isMovie = intent.getBooleanExtra(EXTRA_IS_MOVIE, true)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(EXTRA_TITLE)
        }

        if (isMovie) {
            detailViewModel.getMovieDetail(id).observe(this) { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> setLoading(true)
                        Status.SUCCESS -> {
                            setLoading(false)
                            movie.data?.let { setDetail(it) }
                        }
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(this, "There is error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            detailViewModel.getTvShowDetail(id).observe(this) { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> setLoading(true)
                        Status.SUCCESS -> {
                            setLoading(false)
                            tvShow.data?.let { setDetail(it) }
                        }
                        Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(this, "There is error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
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

    private fun setDetail(movie: MovieDetailEntity) {
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
                movie.genres, "${movie.runtime} minutes"
            )
        }
    }

    private fun setDetail(tvShow: TvShowDetailEntity) {
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
                tvShow.genres, episode, tvShow.status
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