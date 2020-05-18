package com.rosario.covid19.util

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
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
    val userFormat = "dd MMMM yyyy"
    val dayValueInMillis = (24 * 60 * 60 * 1000)
    val simpleDateFormatServer = SimpleDateFormat(serverFormat, Locale.getDefault())
    val simpleDateFormatForUser = SimpleDateFormat(userFormat, Locale.getDefault())
    var selectedDateServer = ""
    var selectedDateByUser = ""


    /**
     * Obtain date to send date to server
     * @param: cal calendar instance
     * @return date in String
     */
    private fun dateFormat(cal: Calendar): String {
        return simpleDateFormatServer.format(cal.time)
    }


    /**
     * Obtain yesterday date to send date to server
     * @param: cal calendar instance
     * @return date in String
     */
    fun getYesterdayDate(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return simpleDateFormatServer.format(cal.time)
    }

    /**
     * Obtain yesterday date to show date to user
     * @param: cal calendar instance
     * @return date in String
     */
    fun getYesterdayDateUser(cal: Calendar): String {
        cal.add(Calendar.DATE, -1)
        return simpleDateFormatForUser.format(cal.time)
    }

    /**
     * Obtain date to show date to user
     * @param: cal calendar instance
     * @return date in String
     */
    private fun userFormatDate(cal: Calendar): String {
        return simpleDateFormatForUser.format(cal.time)
    }

    /**
     * Handle errors from server
     * @param: it Throwable error
     * @return context activity Context
     */
    fun handleError(throwable: Throwable, context: Context): String? {
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    500 -> context.resources.getString(R.string.error_500)
                    401 -> context.resources.getString(R.string.error_401)
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

    fun showDatePicker(context: Context, callback: (String, String) -> Unit) {
        val calendar = Calendar.getInstance()
        try {
            val date: Date = simpleDateFormatServer.parse(selectedDateServer)
            calendar.timeInMillis = date.time
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

        val yearCal = calendar.get(Calendar.YEAR)
        val monthCal = calendar.get(Calendar.MONTH)
        val dayCal = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.let {
                    it.set(Calendar.YEAR, year)
                    it.set(Calendar.MONTH, month)
                    it.set(Calendar.DAY_OF_MONTH, day)
                }

                selectedDateByUser = userFormatDate(calendar)
                selectedDateServer = dateFormat(calendar)
                callback.invoke(selectedDateServer, selectedDateByUser)
            },
            yearCal,
            monthCal,
            dayCal
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - dayValueInMillis
        datePickerDialog.show()
    }

}