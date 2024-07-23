package com.shivayogi.openinapp.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object DateTimeUtils {


    const val DATE_FORMAT_1 = "hh:mm a"
    const val DATE_FORMAT_2 = "h:mm a"
    const val DATE_FORMAT_3 = "dd MMM yyyy"
    const val DATE_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_5 = "dd MMM"


    fun convertDateTime(input: String): String {
        // Define the input format (ISO-8601)
        val inputFormat = SimpleDateFormat(DATE_FORMAT_4, Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        // Parse the input string to Date
        val date = inputFormat.parse(input)
        // Define the desired output format
        val outputFormat = SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault())

        // Format the Date to the desired output format
        return outputFormat.format(date)
    }


    fun formatDate(date: Date): String {
        val format = SimpleDateFormat(DATE_FORMAT_5, Locale.getDefault())
        return format.format(date)
    }
}