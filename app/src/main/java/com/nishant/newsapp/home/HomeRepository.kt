package com.nishant.newsapp.home

import android.content.Context
import com.nishant.newsapp.model.NewsResponse
import com.nishant.newsapp.nonEmptyStringOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge

class HomeRepository(context: Context) {
    private val dataSource: HomeDataSource

    init {
        dataSource = HomeDataSource(context)
    }

    suspend fun fetchNews(query: String?): Flow<NewsResponse> {
        return query?.nonEmptyStringOrNull()?.let { q ->
            dataSource.getNews(q)
        } ?: dataSource.getCountryCode()
            .flatMapMerge { countryCode ->
                dataSource.getTopHeadlines(countryCode)
            }
    }
}