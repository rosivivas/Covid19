package com.rosario.covid19.data

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.data.model.Report
import java.io.IOException

class ReportTypeAdapter : TypeAdapter<DataResponse>() {

    private val gson = Gson()

    override fun write(
        jsonWriter: JsonWriter?,
        DataResponse: DataResponse?
    ) {
        gson.toJson(DataResponse, DataResponse!!::class.java, jsonWriter)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): DataResponse {
        val response: DataResponse
        jsonReader.beginObject()
        jsonReader.nextName()

        when {
            jsonReader.peek() == JsonToken.BEGIN_ARRAY -> {
                jsonReader.beginArray()
                response = DataResponse(Report())
                jsonReader.endArray()
            }
            jsonReader.peek() == JsonToken.BEGIN_OBJECT -> {
                if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                    jsonReader.beginArray()
                    response = DataResponse(Report())
                    jsonReader.endArray()
                } else {
                    response = DataResponse(
                        gson.fromJson<Any>(jsonReader, Report::class.java) as Report
                    )
                }
            }
            else -> {
                throw JsonParseException("Unexpected token " + jsonReader.peek())
            }
        }
        jsonReader.endObject()
        return response
    }
}