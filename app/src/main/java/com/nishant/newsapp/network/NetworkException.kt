package com.nishant.newsapp.network

data class NetworkException(
    val errorCode: Int,
    val url: String?,
    val errorMessage: String?
) : Throwable(errorMessage)