package com.rosario.covid19.ui.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.rosario.covid19.BR


class ReportViewData : BaseObservable() {

    var date: String = ""
    var lastUpdate: String = ""
    @get:Bindable
    var confirmedCases: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmedCases)
        }

    var confirmedDiff: Int = 0

    @get:Bindable
    var deathsCases: Int = 0
        set(value) {
           field = value
           notifyPropertyChanged(BR.deathsCases)
        }
    var deathsDiff: Int = 0
    var recoveredCases: Int = 0
    var recoveredDiff: Int = 0
    var casesActive: Int = 0
    var activeDiff: Int = 0
    var fatalityRate: Double = 0.0



}
