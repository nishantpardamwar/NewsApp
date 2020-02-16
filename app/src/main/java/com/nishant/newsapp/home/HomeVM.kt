package com.nishant.newsapp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nishant.newsapp.ResultWrapper
import com.nishant.newsapp.failure
import com.nishant.newsapp.loading
import com.nishant.newsapp.model.NewsResponse
import com.nishant.newsapp.success
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeVM(application: Application) : AndroidViewModel(application) {

    private val repo = HomeRepository(application)
    val newsLiveData = MutableLiveData<ResultWrapper<NewsResponse, Throwable>>()

    private var apiJob: Job? = null

    fun fetchNewsData(query: String? = null) {
        apiJob?.cancel()
        apiJob = viewModelScope.launch() {
            newsLiveData.loading()
            repo.fetchNews(query)
                .catch { error ->
                    newsLiveData.failure(error)
                }
                .collect {
                    newsLiveData.success(it)
                }
        }
    }
}