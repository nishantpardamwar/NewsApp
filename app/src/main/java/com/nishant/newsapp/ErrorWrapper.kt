package com.nishant.newsapp

data class ApiError(val errorCode: Int, val errorMessage: String?) : Throwable()