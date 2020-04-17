package com.rosario.covid19.domain

import com.rosario.covid19.data.DataApi
import com.rosario.covid19.data.model.DataResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

/**
 * Use case to get Report data
 */
@Singleton
class ReportUseCase @Inject constructor(private val dataApi: DataApi) {

    fun getReport(date: String): Observable<DataResponse> {
        return dataApi.getReport(date)
    }
}