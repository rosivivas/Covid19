package com.rosario.covid19.domain

import com.rosario.covid19.data.DataApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case to get Report data
 */
@Singleton
class ReportUseCase @Inject constructor(private val dataApi: DataApi) {

    suspend fun getReportData(date: String) = dataApi.getReportDataApi(date)

}