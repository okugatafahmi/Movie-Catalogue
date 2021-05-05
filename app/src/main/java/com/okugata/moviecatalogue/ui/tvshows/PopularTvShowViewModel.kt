package com.okugata.moviecatalogue.ui.tvshows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.PopularTvShow
import com.okugata.moviecatalogue.data.PopularTvShowResponse
import com.okugata.moviecatalogue.utils.DeviceLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularTvShowViewModel : ViewModel() {

    companion object {
        const val TAG = "PopularTvShowViewModel"
    }

    private val _popularTvShows = MutableLiveData<List<PopularTvShow>>()
    val popularTvShows: LiveData<List<PopularTvShow>> = _popularTvShows

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularTvShows() {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getPopularTvShow(DeviceLocale.getLanguage())
        client.enqueue(object : Callback<PopularTvShowResponse> {
            override fun onResponse(
                call: Call<PopularTvShowResponse>,
                response: Response<PopularTvShowResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popularTvShows.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularTvShowResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}