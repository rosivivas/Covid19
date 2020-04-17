package com.rosario.covid19.util

import java.text.SimpleDateFormat
import java.util.*

class Util {
    val serverFormat = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(serverFormat, Locale.getDefault())
    val userFormat = "dd 'de' MMMM 'de' yyyy"
    val userSdf = SimpleDateFormat(userFormat, Locale.getDefault())

    fun dateFormat(cal: Calendar): String {
        return sdf.format(cal.time)
    }

    fun getYesterdayDate(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return sdf.format(cal.time)
    }

    fun getYesterdayDateUser(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return userSdf.format(cal.time)
    }

    fun userFormatDate(cal: Calendar): String {
        return userSdf.format(cal.time)
    }
}