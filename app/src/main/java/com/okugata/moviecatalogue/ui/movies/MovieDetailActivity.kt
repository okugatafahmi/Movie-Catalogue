package com.okugata.moviecatalogue.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.data.Movie
import com.okugata.moviecatalogue.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE ="extra_movie"
    }
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE) as Movie

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = movie.title
        }

        with(binding) {
            textTitle.text = movie.title
            textDate.text = movie.releaseDate
            textOverview.text = movie.overview
            Glide.with(this@MovieDetailActivity)
                .load(movie.img)
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