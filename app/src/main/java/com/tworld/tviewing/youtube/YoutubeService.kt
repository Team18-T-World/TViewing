package com.tworld.tviewing.youtube

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("videos")
    fun getYoutubeVideo(
        @Query("part") part:String = "snippet",
        @Query("chart") chart:String = "mostPopular",
        @Query("maxResults") maxResults:Int = 10,
        @Query("regionCode") regionCode:String = "kr",
        @Query("key") apiKey: String
    ): Call<YoutubeResponse>
}