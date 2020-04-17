package com.rosario.covid19.data.model

import com.google.gson.annotations.JsonAdapter
import com.rosario.covid19.data.ReportTypeAdapter

/**
 * Contain report data
 */
@JsonAdapter(ReportTypeAdapter::class)
class DataResponse(var data: Report)