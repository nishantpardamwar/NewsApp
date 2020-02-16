package com.nishant.newsapp

class ResultWrapper<T, E : Throwable> private constructor(
    val isLoading: Boolean,
    val data: T?,
    val error: E?
) {

    val state: State

    init {
        state = if (isLoading)
            State.LOADING
        else if (!isLoading && data != null)
            State.SUCCESS
        else State.FAILURE
    }

    companion object {
        fun <T, E : Throwable> loading() = ResultWrapper<T, E>(true, null, null)
        fun <T, E : Throwable> success(data: T) = ResultWrapper<T, E>(false, data, null)
        fun <T, E : Throwable> failure(error: E) = ResultWrapper<T, E>(false, null, error)
    }

    enum class State {
        LOADING,
        SUCCESS,
        FAILURE
    }
}