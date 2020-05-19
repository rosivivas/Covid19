package com.rosario.covid19.ui.model

import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.util.Mapper

class MapperReport : Mapper<DataResponse, ReportViewData>() {
    override fun mapper(inputElement: DataResponse): ReportViewData {
        val reportViewData = ReportViewData()
        reportViewData.let {
            it.date = inputElement.data.date
            it.lastUpdate = inputElement.data.lastUpdate
            it.confirmedCases = inputElement.data.confirmedCases
            it.confirmedDiff = inputElement.data.confirmedDiff
            it.deathsCases = inputElement.data.deathsCases
            it.deathsDiff = inputElement.data.deathsDiff
            it.recoveredCases = inputElement.data.recoveredCases
            it.recoveredDiff = inputElement.data.recoveredDiff
            it.casesActive = inputElement.data.casesActive
            it.activeDiff = inputElement.data.activeDiff
            it.fatalityRate = inputElement.data.fatalityRate
        }
        return reportViewData
    }
}