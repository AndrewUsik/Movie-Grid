package com.example.moviegrid.extentions

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    return try {
        var format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        val newDate = format.parse(this)
        format = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        format.format(newDate)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}