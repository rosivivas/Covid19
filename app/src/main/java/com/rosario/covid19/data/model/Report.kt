package com.rosario.covid19.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Contain attributes to show specific data of COVID-19 by specific date
 */
data class Report(
    @SerializedName("date")
    var date: String = "",
    @SerializedName("last_update")
    var lastUpdate: String = "",
    @SerializedName("confirmed")
    var confirmedCases: Int = 0,
    @SerializedName("confirmed_diff")
    var confirmedDiff: Int = 0,
    @SerializedName("deaths")
    var deathsCases: Int = 0,
    @SerializedName("deaths_diff")
    var deathsDiff: Int = 0,
    @SerializedName("recovered")
    var recoveredCases: Int = 0,
    @SerializedName("recovered_diff")
    var recoveredDiff: Int = 0,
    @SerializedName("active")
    var casesActive: Int = 0,
    @SerializedName("active_diff")
    var activeDiff: Int = 0,
    @SerializedName("fatality_rate")
    var fatalityRate: Double = 0.0
) : Serializable