package com.okugata.moviecatalogue.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DeviceLocale {
    fun convertDate(date: String) =
        SimpleDateFormat("yyyy-MM-dd", Locale("en")).parse(date)?.let {
            DateFormat.getDateInstance().format(it)
        } ?: date

    fun getLanguage() = "${Locale.getDefault().language}-${Locale.getDefault().country}"
}