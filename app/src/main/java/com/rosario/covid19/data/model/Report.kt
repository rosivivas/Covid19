package com.rosario.covid19.data.model

data class Report(
    var date: String,
    var last_update: String,
    var confirmed: Int,
    var confirmed_diff: Int,
    var deaths: Int,
    var deaths_diff: Int,
    var recovered: Int,
    var recovered_diff: Int,
    var active: Int,
    var active_diff: Int,
    var fatality_rate: Double
)