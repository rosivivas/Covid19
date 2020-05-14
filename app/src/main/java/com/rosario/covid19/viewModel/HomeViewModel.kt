package com.rosario.covid19.viewModel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.rosario.covid19.R
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.data.model.Report
import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Handle connection to server and show data to UI
 */
class HomeViewModel @Inject constructor(
    private val reportUseCase: ReportUseCase
) : ViewModel() {

    var confirmed = MutableLiveData<String>().apply { postValue("") }
    var deaths = MutableLiveData<String>().apply { postValue("") }
    lateinit var reportLiveData: LiveData<Resource<DataResponse>>

    /**
     *  Get data from server
     * @param date
     */
    fun getReport(date: String) {
        reportUseCase.getReport(date)
       /* reportLiveData = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = reportUseCase.getReport(date)))
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }*/
    }

}