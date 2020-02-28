package com.zuehlke.training.easycv.util

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    fun formatNormalDate(date: Long): String {
        return SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).format(Date(date))
    }
}