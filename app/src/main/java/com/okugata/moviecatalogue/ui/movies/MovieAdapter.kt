package com.okugata.moviecatalogue.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.data.Movie
import com.okugata.moviecatalogue.databinding.ListItemsBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Movie>()

    fun setMovies(movies: List<Movie>?) {
        movies?.let {
            listMovies.clear()
            listMovies.addAll(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.releaseDate
                itemView.setOnClickListener {
                    // TODO: To detail activity
                    /*val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, movie.movieId)
                    itemView.context.startActivity(intent)*/
                }
                Glide.with(itemView.context)
                    .load(movie.img)
                    .into(imgPoster)
            }
        }
    }
}