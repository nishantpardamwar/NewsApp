package com.nishant.newsapp.home

import android.content.Context
import com.nishant.newsapp.model.NewsResponse
import com.nishant.newsapp.network.NetworkClient
import com.nishant.newsapp.network.NetworkException
import com.nishant.newsapp.nonEmptyStringOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HomeDataSource(context: Context) {
    private val API_KEY = "YOUR_API_KEY"

    private val DEFAULT_COUNTRY = "in" //india

    suspend fun getCountryCode(): Flow<String> {
        return flow {
            val response = NetworkClient.client.getIpInfo("http://ip-api.com/json")

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it.countryCode.toLowerCase())
                    return@flow
                }
            }
            emit(DEFAULT_COUNTRY)
        }.catch { emit(DEFAULT_COUNTRY) }
    }

    suspend fun getTopHeadlines(country: String): Flow<NewsResponse> {
        return flow {
            val response = NetworkClient.client.getTopHeadlines(API_KEY, country)

            successOrThrow(response, this)
        }
    }

    suspend fun getNews(
        query: String
    ): Flow<NewsResponse> {
        return flow {
            val response = NetworkClient.client.getNews(
                API_KEY,
                query
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

        throw NetworkException(
            response.code(),
            response.raw().request().url().toString(),
            errorMessage
        )
    }
}