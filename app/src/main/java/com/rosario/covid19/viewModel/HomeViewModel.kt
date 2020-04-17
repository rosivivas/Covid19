package com.rosario.covid19.viewModel

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.rosario.covid19.R
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
class HomeViewModel @Inject constructor(
    private val reportUseCase: ReportUseCase,
    private val context: Context
) {

    private lateinit var subscription: Disposable
    var response = MutableLiveData<Report>()
    var error = MutableLiveData<Throwable>()
    var progressBar = MutableLiveData<Int>().apply { postValue(View.GONE) }
    var dataVisibility = MutableLiveData<Int>().apply { postValue(View.GONE) }
    var confirmed = MutableLiveData<String>().apply { postValue("") }
    var deaths = MutableLiveData<String>().apply { postValue("") }


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
        if (result!!.data.size > 0) {
            if (result.data.containsKey("confirmed")) {
                val dataConfirmed  = result.data["confirmed"] as Double
                val dataDeaths = result.data["deaths"] as Double

                confirmed.postValue(
                    String.format(
                        context.resources.getString(R.string.cases_confirmed),
                        dataConfirmed.toInt()
                    )
                )
                deaths.postValue(
                    String.format(
                        context.resources.getString(R.string.deaths_confirmed),
                        dataDeaths.toInt()
                    )
                )
            }
        } else {
            confirmed.postValue(
                String.format(
                    context.resources.getString(R.string.cases_confirmed),
                    0
                )
            )
            deaths.postValue(
                String.format(
                    context.resources.getString(R.string.deaths_confirmed),
                    0
                )
            )
        }
        dataVisibility.postValue(View.VISIBLE)
    }

    /**
     *  Get error from server
     */
    private fun onError(result: Throwable) {
        error.value = result
    }

}