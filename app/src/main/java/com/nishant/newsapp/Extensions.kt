package com.nishant.newsapp

import android.view.View
import androidx.lifecycle.MutableLiveData

var View.isVisible: Boolean
    get() {
        return if (visibility == View.VISIBLE) true else false
    }
    set(value) {
        if (value) {
            visibility = View.VISIBLE
        } else {
            visibility = View.GONE
        }
    }


var View.isInvisible: Boolean
    get() {
        return if (visibility == View.INVISIBLE) true else false
    }
    set(value) {
        if (value) {
            visibility = View.INVISIBLE
        } else {
            visibility = View.VISIBLE
        }
    }

fun String?.nonEmptyStringOrNull(): String? {
    return if (this != null && this.trim().length > 0) this else null
}

fun <T, E : Throwable> MutableLiveData<ResultWrapper<T, E>>.loading() {
    if (this != null) value = ResultWrapper.loading()
}

fun <T, E : Throwable> MutableLiveData<ResultWrapper<T, E>>.success(data: T) {
    if (this != null) value = ResultWrapper.success(data)
}

fun <T, E : Throwable> MutableLiveData<ResultWrapper<T, E>>.failure(error: E) {
    if (this != null) value = ResultWrapper.failure(error)
}