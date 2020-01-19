package com.example.moviegrid.extentions

import com.example.moviegrid.utils.POSTER_URL

fun String?.parseDouble(): Double? {
    return if (this.isNullOrBlank().not()) {
        return try {
            this?.let {
                java.lang.Double.parseDouble(this)
            }
        } catch (e: Exception) {
            null
        }
    } else null
}

fun String.getPosterUrl(): String {
    return "${POSTER_URL}$this"
}