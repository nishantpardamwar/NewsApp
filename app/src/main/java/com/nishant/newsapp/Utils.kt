package com.nishant.newsapp

import android.content.res.Resources

fun dpToPx(dp: Float): Float {
    return dp * Resources.getSystem().displayMetrics.density
}

fun pxToDp(px: Float): Float {
    return px / Resources.getSystem().displayMetrics.density
}