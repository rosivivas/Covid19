package com.rosario.covid19.ui.model


data class ReportViewData(var date: String = "",
                          var lastUpdate: String = "",
                          var confirmedCases: Int = 0,
                          var confirmedDiff: Int = 0,
                          var deathsCases: Int = 0,
                          var deathsDiff: Int = 0,
                          var recoveredCases: Int = 0,
                          var recoveredDiff: Int = 0,
                          var casesActive: Int = 0,
                          var activeDiff: Int = 0,
                          var fatalityRate: Double = 0.0) {

}
