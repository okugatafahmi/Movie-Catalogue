package com.okugata.moviecatalogue.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.R
import com.okugata.moviecatalogue.core.data.Resource
import com.okugata.moviecatalogue.core.data.source.remote.network.ApiService.Companion.getImageUrl
import com.okugata.moviecatalogue.core.domain.model.Movie
import com.okugata.moviecatalogue.core.domain.model.TvShow
import com.okugata.moviecatalogue.core.utils.DeviceLocale.convertDate
import com.okugata.moviecatalogue.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_IS_MOVIE = "extra_is_movie"
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var isMovie = true
    private var movie: Movie? = null
    private var tvShow: TvShow? = null
    private var id = 0

    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getIntExtra(EXTRA_ID, 0)
        isMovie = intent.getBooleanExtra(EXTRA_IS_MOVIE, true)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = intent.getStringExtra(EXTRA_TITLE)
        }
    }

    private fun setupViewModel() {
        if (isMovie) {
            detailViewModel.getMovieDetail(id).observe(this) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> setLoading(true)
                        is Resource.Success -> {
                            setLoading(false)
                            movie.data?.let {
                                setDetail(it)
                                setFavoriteState(it.favorite)
                            }
                        }
                        is Resource.Error -> {
                            setLoading(false)
                            Toast.makeText(this, "There is error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        } else {
            detailViewModel.getTvShowDetail(id).observe(this) { tvShow ->
                if (tvShow != null) {
                    when (tvShow) {
                        is Resource.Loading -> setLoading(true)
                        is Resource.Success -> {
                            setLoading(false)
                            tvShow.data?.let {
                                setDetail(it)
                                setFavoriteState(it.favorite)
                            }
                        }
                        is Resource.Error -> {
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
        this.menu = menu
        setupViewModel()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.share -> shareItem()
            R.id.favorite -> {
                if (isMovie) {
                    movie?.let { detailViewModel.setMovieFavorite(it) }
                } else {
                    tvShow?.let { detailViewModel.setTvShowFavorite(it) }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        val menuItem = menu?.findItem(R.id.favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
        }
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

    private fun setDetail(movie: Movie) {
        this.movie = movie
        supportActionBar?.title = movie.title
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(getImageUrl(movie.posterPath))
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

    private fun setDetail(tvShow: TvShow) {
        this.tvShow = tvShow
        supportActionBar?.title = tvShow.name
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(getImageUrl(tvShow.posterPath))
                .placeholder(ColorDrawable(Color.GRAY))
                .into(imagePoster)
            textTitle.text = tvShow.name
            textDate.text = convertDate(tvShow.firstAirDate)
            textOverview.text = tvShow.overview
            val episode = resources.getQuantityString(
                R.plurals.numberOfEpisode,
                tvShow.numberOfEpisodes?:0,
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