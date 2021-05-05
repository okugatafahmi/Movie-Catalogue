package com.okugata.moviecatalogue.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.PopularMovie
import com.okugata.moviecatalogue.data.PopularMovieResponse
import com.okugata.moviecatalogue.utils.DeviceLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMovieViewModel : ViewModel() {
    companion object {
        const val TAG = "MovieViewModel"
    }

    private val _popularMovies = MutableLiveData<List<PopularMovie>>()
    val popularMovies: LiveData<List<PopularMovie>> = _popularMovies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPopularMovies() {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getPopularMovie(DeviceLocale.getLanguage())
        client.enqueue(object : Callback<PopularMovieResponse>{
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _popularMovies.value = response.body()?.results
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}