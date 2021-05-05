package com.okugata.moviecatalogue.ui.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.data.Movie
import com.okugata.moviecatalogue.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieViewModel: MovieViewModel
    private var movie: Movie ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
        if (movieViewModel.movie == null) {
            movieViewModel.movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE) as Movie
        }

        movie = movieViewModel.movie

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = movie?.title
        }

        with(binding) {
            Glide.with(this@MovieDetailActivity)
                .load(movie?.img)
                .into(imagePoster)
            textTitle.text = movie?.title
            textDate.text = movie?.releaseDate
            textOverview.text = movie?.overview
            textDetail.text = getString(R.string.separator_2, "\u2022",
                movie?.genre, movie?.duration)
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
                getString(R.string.share_movie, movie?.title)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}