package com.rosario.covid19.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.data.model.Report
import com.rosario.covid19.domain.ReportUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Handle connection to server and show data to UI
 */
class HomeViewModel @Inject constructor(private val reportUseCase: ReportUseCase) {

    private lateinit var subscription: Disposable
    var response = MutableLiveData<Report>()
    var error = MutableLiveData<Throwable>()
    var progressBar = MutableLiveData<Int>().apply { postValue(View.GONE) }
    var dataVisibility = MutableLiveData<Int>().apply { postValue(View.GONE) }

    /**
     *  Get data from server
     * @param date
     */
    fun getReport(date: String) {
        subscription = reportUseCase.getReport(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressStart() }
            .doOnTerminate { hideProgress() }
            .subscribe({ result -> registerSuccess(result) },
                { error -> onError(error) })
    }

    /**
     *  Hide progress bar in UI
     */
    private fun hideProgress() {
        progressBar.postValue(View.GONE)
    }

    /**
     *  Show progress bar to UI
     */
    private fun progressStart() {
        progressBar.postValue(View.VISIBLE)
    }

    /**
     *  Get success response data
     */
    private fun registerSuccess(result: DataResponse?) {
        response.value = result!!.data
        dataVisibility.postValue(View.VISIBLE)
    }

    /**
     *  Get error from server
     */
    private fun onError(result: Throwable) {
        error.value = result
    }

}