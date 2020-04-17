package com.rosario.covid19.domain

import com.rosario.covid19.data.DataApi
import com.rosario.covid19.data.model.DataResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class ReportUseCase @Inject constructor(private val dataApi: DataApi) {

    fun getReport(date: String): Observable<DataResponse> {
        val map = HashMap<String, String>()
        map["date"] = date
        return dataApi.getReport(date)
    }
}