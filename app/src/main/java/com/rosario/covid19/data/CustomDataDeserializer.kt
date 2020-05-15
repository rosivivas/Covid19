package com.rosario.covid19.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.data.model.Report
import com.rosario.covid19.util.*
import java.lang.reflect.Type


class CustomDataDeserializer : JsonDeserializer<DataResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DataResponse {
        val jsonObject = json!!.asJsonObject
        val jsonData = jsonObject.get(DATA)
        return if (jsonData is JsonObject) {
            val report = Report()
            report.date = jsonData.get(DATE).asString
            report.lastUpdate = jsonData.get(LAST_UPDATE).asString
            report.confirmedCases = jsonData.get(DATE).asInt
            report.confirmedDiff = jsonData.get(DATE).asInt
            report.deathsCases = jsonData.get(DATE).asInt
            report.deathsDiff = jsonData.get(DATE).asInt
            report.recoveredCases = jsonData.get(DATE).asInt
            report.recoveredDiff = jsonData.get(DATE).asInt
            report.casesActive = jsonData.get(DATE).asInt
            report.activeDiff = jsonData.get(DATE).asInt
            report.fatalityRate = jsonData.get(DATE).asDouble

            DataResponse(report)
        } else
            DataResponse(Report())
    }
}