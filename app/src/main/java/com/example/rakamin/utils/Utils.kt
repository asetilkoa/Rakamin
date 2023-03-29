package com.example.rakamin.utils

import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getCountry(): String? {
        val locale = Locale.getDefault()
        val strCountry = locale.country
        return strCountry.toLowerCase()
    }

    fun DateFormat(dateNews: String?): String? {
        val isDate: String?
        val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale(getCountry()))
        isDate = try {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(dateNews)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            dateNews
        }
        return isDate
    }

    fun DateTimeHourAgo(dateTime: String?): String? {
        val prettyTime = PrettyTime(Locale.getDefault())
        var isTime: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = simpleDateFormat.parse(dateTime)
            isTime = prettyTime.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

}