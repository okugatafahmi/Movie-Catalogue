package com.okugata.moviecatalogue.ui.tvshows

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okugata.moviecatalogue.api.ApiConfig.IMAGE_BASE_URL
import com.okugata.moviecatalogue.data.source.remote.response.PopularTvShow
import com.okugata.moviecatalogue.databinding.ListItemsBinding
import com.okugata.moviecatalogue.utils.DeviceLocale

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<PopularTvShow>()

    fun setTvShows(tvShows: List<PopularTvShow>) {
        listTvShows.clear()
        listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val listItemsBinding =
            ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(listItemsBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size


    class TvShowViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: PopularTvShow) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemDate.text = DeviceLocale.convertDate(tvShow.firstAirDate)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, TvShowDetailActivity::class.java).apply {
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_ID, tvShow.id)
                        putExtra(TvShowDetailActivity.EXTRA_TV_SHOW_TITLE, tvShow.name)
                    }
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load("$IMAGE_BASE_URL${tvShow.posterPath}")
                    .placeholder(ColorDrawable(Color.GRAY))
                    .into(imgPoster)
            }
        }
    }
}