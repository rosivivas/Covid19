package com.rosario.covid19.data

import com.google.gson.*
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
           DataResponse(Gson().fromJson<Any>(jsonData, Report::class.java) as Report)
        } else
            DataResponse(Report())
    }
}