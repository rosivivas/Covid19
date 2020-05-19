package com.rosario.covid19.ui.model

import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.util.Mapper

class MapperReport : Mapper<DataResponse, ReportViewData>() {
    override fun mapper(inputElement: DataResponse): ReportViewData {
        return ReportViewData(
            date = inputElement.data.date,
            lastUpdate = inputElement.data.lastUpdate,
            confirmedCases = inputElement.data.confirmedCases,
            confirmedDiff = inputElement.data.confirmedDiff,
            deathsCases = inputElement.data.deathsCases,
            deathsDiff = inputElement.data.deathsDiff,
            recoveredCases = inputElement.data.recoveredCases,
            recoveredDiff = inputElement.data.recoveredDiff,
            casesActive = inputElement.data.casesActive,
            activeDiff = inputElement.data.activeDiff,
            fatalityRate = inputElement.data.fatalityRate
        )
    }
}