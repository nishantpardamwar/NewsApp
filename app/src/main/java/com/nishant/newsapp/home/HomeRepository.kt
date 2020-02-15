package com.nishant.newsapp.home

import android.content.Context
import com.nishant.newsapp.model.NewsResponse
import kotlinx.coroutines.flow.Flow

class HomeRepository(context: Context) {
    private val SORT_BY = "publishedAt"
    private val FROM_DATE = "2020-01-01"

    private val dataSource: HomeDataSource

    init {
        dataSource = HomeDataSource(context)
    }

    suspend fun fetchTopHeadlines(): Flow<NewsResponse> {
        return dataSource.getTopHeadlines("in")
    }

    suspend fun fetchNews(query: String?): Flow<NewsResponse> {
        return dataSource.getNews(query, FROM_DATE, SORT_BY)
    }
}