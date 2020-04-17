package com.rosario.covid19.data.model

/**
 * Contain attributes to show specific data of COVID-19 by specific date
 */
data class Report(
    var date: String = "",
    var last_update: String = "",
    var confirmed: Int = 0,
    var confirmed_diff: Int = 0,
    var deaths: Int = 0,
    var deaths_diff: Int = 0,
    var recovered: Int = 0,
    var recovered_diff: Int = 0,
    var active: Int = 0,
    var active_diff: Int = 0,
    var fatality_rate: Double = 0.0
)