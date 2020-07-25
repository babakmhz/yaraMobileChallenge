package com.android.babakmhz.yaramobilechallenge.utils

object CommonUtils {
    fun getRatingValue(rating: String): Float {
        return try {
            (rating.toFloat() * 5) / 10
        } catch (e: Exception) {
            0.0F
        }
    }
}