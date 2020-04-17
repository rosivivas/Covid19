package com.rosario.covid19.data

import com.rosario.covid19.data.model.DataResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DataApi {
    @Headers("X-RapidAPI-Key: 96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
    @GET("reports/total")
    fun getReport(@Query("date") map: String): Observable<DataResponse>

}