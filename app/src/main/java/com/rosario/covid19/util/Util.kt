package com.rosario.covid19.util

import java.text.SimpleDateFormat
import java.util.*


class Util {
    val serverFormat = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(serverFormat, Locale.getDefault())


    fun dateFormat(cal: Calendar): String {
        return sdf.format(cal.time)
    }

    fun getYesterdayDate(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return sdf.format(cal.time)
    }

    fun userFormatDate(cal: Calendar): String {
        val userFormat = "dd 'de' MMMM 'de' yyyy"
        val userSdf = SimpleDateFormat(userFormat, Locale.getDefault())
        return userSdf.format(cal.time)
    }
}