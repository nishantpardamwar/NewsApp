package com.nishant.newsapp

class ResultWrapper<T, E : Throwable> private constructor(
    private val isLoading: Boolean,
    val data: T?,
    val error: E?
) {

    companion object {
        fun <T, E : Throwable> loading() = ResultWrapper<T, E>(true, null, null)
        fun <T, E : Throwable> success(data: T) = ResultWrapper<T, E>(false, data, null)
        fun <T, E : Throwable> failure(error: E) = ResultWrapper<T, E>(false, null, error)
    }


    fun isLoading(): Boolean = isLoading

    fun isSuccess(): Boolean = (!isLoading && error == null && data != null)

    fun isFailure(): Boolean = (!isLoading && data == null && error != null)
}