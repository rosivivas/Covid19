package com.rosario.covid19.ui.home

import androidx.lifecycle.*
import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.ui.model.MapperReport
import com.rosario.covid19.ui.model.ReportViewData
import com.rosario.covid19.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Handle connection to server and show data to UI
 */
class HomeViewModel @Inject constructor(
    private val reportUseCase: ReportUseCase
) : ViewModel() {

    var fetchReportViewDataLiveData =
        MutableLiveData<Resource<ReportViewData>>()
    var reportLiveData =
        MutableLiveData<ReportViewData>()

    var reportViewDataValue: ReportViewData
        get() = reportLiveData.value ?: ReportViewData()
        set(value) {
            reportLiveData.value = value
        }

    /**
     *  Get data from server
     * @param date
     */
    fun getReport(date: String) {
        viewModelScope.launch {
            fetchReportViewDataLiveData.postValue(Resource.loading())

            try {
                reportViewDataValue = MapperReport().mapper(reportUseCase.getReportData(date))
                fetchReportViewDataLiveData.postValue(Resource.success(data = reportViewDataValue))
            } catch (e: Throwable) {
                fetchReportViewDataLiveData.postValue(Resource.error(error = e))
            }
        }
    }
}