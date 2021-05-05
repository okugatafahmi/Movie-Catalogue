package com.okugata.moviecatalogue.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.MovieDetailResponse
import com.okugata.moviecatalogue.utils.DeviceLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {

    companion object {
        const val TAG = "MovieDetailViewModel"
    }
    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse> = _movieDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getMovieDetail(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getMovieDetail(id, DeviceLocale.getLanguage())
        client.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _movieDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}