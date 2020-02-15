package com.nishant.newsapp.network

import com.nishant.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String
    ): Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String,
        @Query("fromDate") fromDate: String,
        @Query("sortBy") sortBy: String
    ): Response<NewsResponse>
}
