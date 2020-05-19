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

    val reportViewData: LiveData<Resource<ReportViewData>>?
        get() = fetchReportViewDataLiveData

    var reportData = ReportViewData()

    /**
     *  Get data from server
     * @param date
     */
    fun getReport(date: String) {
        viewModelScope.launch {
            fetchReportViewDataLiveData.postValue(Resource.loading(data = reportData))
            try {
                reportData = MapperReport().mapper(reportUseCase.getReportData(date))
                fetchReportViewDataLiveData.postValue(Resource.success(data = reportData))
            } catch (e: Throwable) {
                fetchReportViewDataLiveData.postValue(Resource.error(error = e))
            }
        }
    }
}