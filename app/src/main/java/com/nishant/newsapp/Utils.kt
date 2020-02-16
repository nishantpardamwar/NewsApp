package com.nishant.newsapp

import android.content.res.Resources
import java.text.SimpleDateFormat
import java.util.*

fun dpToPx(dp: Float): Float {
    return dp * Resources.getSystem().displayMetrics.density
}

fun pxToDp(px: Float): Float {
    return px / Resources.getSystem().displayMetrics.density
}

fun getDisplayDate(dateString: String?): String? {
    try {
        dateString?.let {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
            inputFormat.parse(dateString)?.let { date ->
                return formatter.format(date)
            }
        }
    } catch (e: Exception) {
    }
    return null
}