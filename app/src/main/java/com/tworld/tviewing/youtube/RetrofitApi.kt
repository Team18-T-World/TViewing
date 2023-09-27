package com.tworld.tviewing.youtube

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).baseUrl(
            BASE_URL).build()
    }

    val youtubeService: YoutubeService by lazy {
        retrofit.create(YoutubeService::class.java)
    }

}