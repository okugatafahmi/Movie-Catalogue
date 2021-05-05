package com.okugata.moviecatalogue.ui.tvshows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okugata.moviecatalogue.api.ApiConfig
import com.okugata.moviecatalogue.data.TvShowDetailResponse
import com.okugata.moviecatalogue.utils.DeviceLocale
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowDetailViewModel : ViewModel() {
    companion object {
        const val TAG = "TvShowDetailViewModel"
    }
    private val _tvShowDetail = MutableLiveData<TvShowDetailResponse>()
    val tvShowDetail: LiveData<TvShowDetailResponse> = _tvShowDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTvShowDetail(id: Int) {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
            .getTvShowDetail(id, DeviceLocale.getLanguage())
        client.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(
                call: Call<TvShowDetailResponse>,
                response: Response<TvShowDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _tvShowDetail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
}