package com.example.weatherApplication.commonUtils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(
    fromPattern: String = "yyyy-MM-dd HH:mm:ss",
    toPattern: String = "EEE",
    locale: Locale = Locale.ENGLISH,
    timeZone: TimeZone = TimeZone.getDefault(),
): String? {
    try {
        val date =
            SimpleDateFormat(fromPattern, locale).apply { this.timeZone = timeZone }.parse(this)
        return SimpleDateFormat(toPattern, locale).apply { this.timeZone = timeZone }.format(date)
    } catch (e: Exception) {
        return null
    }
}

