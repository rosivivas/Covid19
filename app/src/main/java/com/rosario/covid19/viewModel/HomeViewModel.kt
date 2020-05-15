package com.rosario.covid19.viewModel

import androidx.lifecycle.*
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.util.Resource
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * Handle connection to server and show data to UI
 */
class HomeViewModel @Inject constructor(
    private val reportUseCase: ReportUseCase
) : ViewModel() {

    var confirmed = MutableLiveData<String>().apply { postValue("") }
    var deaths = MutableLiveData<String>().apply { postValue("") }
    var reportMutableLiveData =
        MutableLiveData<Resource<DataResponse>>().apply { postValue(Resource.loading(data = null)) }

    /**
     *  Get data from server
     * @param date
     */
    fun getReport(date: String) {
        viewModelScope.launch {
            reportMutableLiveData.postValue(Resource.loading(data = null))
            try {
                reportMutableLiveData.postValue(Resource.success(data = reportUseCase.getReport(date)))
            } catch (e: Exception) {
                reportMutableLiveData.postValue(Resource.error(data = null, message = e.message.toString()))
            }
        }
    }
}