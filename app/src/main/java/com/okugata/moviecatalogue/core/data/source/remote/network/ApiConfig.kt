package com.okugata.moviecatalogue.core.data.source.remote.network

import com.okugata.moviecatalogue.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    fun getApiService(): ApiService {
        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(loggingInterceptor)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
}