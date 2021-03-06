package com.rosario.covid19.data

import com.rosario.covid19.data.model.DataResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Contain specific URL
 */
interface DataApi {
    @GET("reports/total")
    fun getReport(@Query("date") map: String): Observable<DataResponse>

}