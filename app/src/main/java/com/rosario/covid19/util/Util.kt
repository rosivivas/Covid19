package com.rosario.covid19.util

import android.content.Context
import android.provider.Settings.Global.getString
import com.rosario.covid19.R
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Uttil class
 */
class Util {
    val serverFormat = "yyyy-MM-dd"
    val sdf = SimpleDateFormat(serverFormat, Locale.getDefault())
    val userFormat = "dd MMMM yyyy"
    val userSdf = SimpleDateFormat(userFormat, Locale.getDefault())

    /**
     * Obtain date to send date to server
     * @param: cal calendar instance
     * @return date in String
     */
    fun dateFormat(cal: Calendar): String {
        return sdf.format(cal.time)
    }


    /**
     * Obtain yesterday date to send date to server
     * @param: cal calendar instance
     * @return date in String
     */
    fun getYesterdayDate(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return sdf.format(cal.time)
    }

    /**
     * Obtain yesterday date to show date to user
     * @param: cal calendar instance
     * @return date in String
     */
    fun getYesterdayDateUser(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return userSdf.format(cal.time)
    }

    /**
     * Obtain date to show date to user
     * @param: cal calendar instance
     * @return date in String
     */
    fun userFormatDate(cal: Calendar): String {
        return userSdf.format(cal.time)
    }

    /**
     * Handle errors from server
     * @param: it Throwable error
     * @return context activity Context
     */
    fun handleError(throwable: Throwable, context: Context): String? {
        return when (throwable) {
            is HttpException -> {
                when {
                    throwable.code() == 500 -> context.resources.getString(R.string.error_500)
                    throwable.code() == 401 -> context.resources.getString(R.string.error_401)
                    else -> context.resources.getString(R.string.error)
                }
            }
            is IOException -> {
                context.resources.getString(R.string.network_error)
            }
            else -> {
                context.resources.getString(R.string.error)
            }
        }
    }

}