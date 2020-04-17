package com.rosario.covid19.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.data.model.Report
import com.rosario.covid19.domain.ReportUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val reportUseCase: ReportUseCase)
{
    private lateinit var subscription: Disposable
    val progressBar: MutableLiveData<Int> = MutableLiveData()
    var response = MutableLiveData<Report>()
    var error = MutableLiveData<String>()

    fun getReport(date: String) {
        subscription = reportUseCase.getReport(date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progressStart() }
            .doOnTerminate { hideProgress() }
            .subscribe({ result -> registerSuccess(result) },
                { error -> onError(error) })
    }

    private fun hideProgress() {
        progressBar.value = View.GONE
    }

    private fun progressStart() {
        progressBar.value = View.VISIBLE
    }

    private fun registerSuccess(result: DataResponse?) {
        response.value = result!!.data
    }

    private fun onError(result: Throwable) {
        if (result is HttpException) {
            var responseBody = result.response().errorBody() as ResponseBody
            getErrorMessage(responseBody)
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody) {
        try {
            val jsonError = JSONObject(responseBody.string())
            error.value = jsonError.getString("detail")

        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

    }

}