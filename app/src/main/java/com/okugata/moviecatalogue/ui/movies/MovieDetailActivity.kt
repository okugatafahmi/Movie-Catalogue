package com.okugata.moviecatalogue.ui.movies

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
import com.okugata.moviecatalogue.data.source.remote.response.MovieDetailResponse
import com.okugata.moviecatalogue.databinding.ActivityMovieDetailBinding
import com.okugata.moviecatalogue.utils.DeviceLocale
import com.okugata.moviecatalogue.viewmodel.MovieDetailViewModel
import com.okugata.moviecatalogue.viewmodel.ViewModelFactory

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_MOVIE_TITLE = "extra_movie_title"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private var movie: MovieDetailResponse? = null
    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieDetailViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance()
        )[MovieDetailViewModel::class.java]
        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        }

        setLoading(true)
        movieDetailViewModel.getMovieDetail(movieId).observe(this) { movie ->
            setLoading(false)
            movie?.let { setDetail(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.share -> shareMovie()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareMovie() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(
                    R.string.share_movie,
                    movie?.title
                )
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
            Glide.with(this@MovieDetailActivity)
                .load("${ApiConfig.IMAGE_BASE_URL}${movie.posterPath}")
                .placeholder(ColorDrawable(Color.GRAY))
                .into(imagePoster)
            textTitle.text = movie.title
            textDate.text = DeviceLocale.convertDate(movie.releaseDate)
            textOverview.text = movie.overview
            textDetail.text = getString(
                R.string.separator_2, "\u2022",
                movie.getGenres(), "${movie.runtime} minutes"
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