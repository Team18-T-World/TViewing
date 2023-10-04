package com.tworld.tviewing.youtube

import com.tworld.tviewing.search.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {
    @GET("videos")
    fun getYoutubeVideo(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = 10,
        @Query("regionCode") regionCode: String = "kr",
        @Query("key") apiKey: String
    ): Call<YoutubeResponse>

    @GET("videos")
    fun getYoutubeVideoByCategory(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = 10,
        @Query("regionCode") regionCode: String = "kr",
        @Query("categoryId") categoryId: String,
        @Query("key") apiKey: String
    ): Call<YoutubeResponse>


    @GET("search")
    fun getSearchService(
        @Query("part") part: String = "snippet",
        @Query("q") q: String = "검색어",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = 10,
        @Query("regionCode") regionCode: String = "kr",
        @Query("key") apiKey: String
    ): Call<SearchResponse>
}