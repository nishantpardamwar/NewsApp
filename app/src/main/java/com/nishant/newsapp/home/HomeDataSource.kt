package com.nishant.newsapp.home

import android.content.Context
import com.nishant.newsapp.model.NewsResponse
import com.nishant.newsapp.network.NetworkClient
import com.nishant.newsapp.nonEmptyStringOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HomeDataSource(context: Context) {
    private val API_KEY = "YOUR_API_KEY"

    suspend fun getCountryCode(): Flow<String> {
        return flow {
            val response = NetworkClient.client.getIpInfo("http://ip-api.com/json")

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it.countryCode.toLowerCase())
                    return@flow
                }
            }
            emit("in")
        }.catch { emit("in") }
    }

    suspend fun getTopHeadlines(country: String): Flow<NewsResponse> {
        return flow {
            val response = NetworkClient.client.getTopHeadlines(API_KEY, country)

            successOrThrow(response, this)
        }
    }

    suspend fun getNews(
        query: String? = null,
        fromDate: String,
        sortBy: String
    ): Flow<NewsResponse> {
        return flow {
            val response = NetworkClient.client.getNews(
                API_KEY,
                if (query == null) "" else query,
                fromDate,
                sortBy
            )

            successOrThrow(response, this)
        }
    }


    private suspend fun <T> successOrThrow(response: Response<T>, flowCollector: FlowCollector<T>) {
        if (response.isSuccessful) {
            response.body()?.let {
                flowCollector.emit(it)
                return
            }
        }

        val errorMessage =
            response.errorBody()?.string()?.nonEmptyStringOrNull()
                ?: "Something went wrong."
        throw Exception(errorMessage)
    }
}