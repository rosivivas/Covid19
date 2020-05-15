package com.rosario.covid19.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rosario.covid19.R
import com.rosario.covid19.data.model.DataResponse
import com.rosario.covid19.databinding.ActivityMainBinding
import com.rosario.covid19.util.Status
import com.rosario.covid19.util.Util
import com.rosario.covid19.viewModel.HomeViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Handle UI for Home
 */
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    var selectedDate = ""

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initBinding()
        prepareElements()
        loadYesterdayData()
        viewObservers()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this
    }

    private fun loadYesterdayData() {
        dateTitleText.text = Util().getYesterdayDateUser(Calendar.getInstance())
        homeViewModel.getReport(Util().getYesterdayDate(Calendar.getInstance()))
    }

    private fun prepareElements() {
        buttonDisplayCalendar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonDisplayCalendar -> {
                showDatePicker()
            }
        }
    }

    /**
     * Show date picker dialog and set max date
     */
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date: Date = simpleDateFormat.parse(selectedDate)
            calendar.timeInMillis = date.time
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

        val yearCal = calendar.get(Calendar.YEAR)
        val monthCal = calendar.get(Calendar.MONTH)
        val dayCal = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            OnDateSetListener { _, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                dateTitleText.text = Util().userFormatDate(calendar)
                selectedDate = Util().dateFormat(calendar)
                homeViewModel.getReport(selectedDate)
            },
            yearCal,
            monthCal,
            dayCal
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    private fun viewObservers() {
        homeViewModel.reportMutableLiveData.observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBarHome.visibility = View.GONE
                        errorText.visibility = View.GONE
                        resource.data?.let { report -> showData(report) }
                    }
                    Status.ERROR -> {
                        progressBarHome.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = Util().handleError(it.error!!,this)
                    }
                    Status.LOADING -> {
                        errorText.visibility = View.GONE
                        progressBarHome.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun showData(report: DataResponse) {
        casesConfirmedText.text = String.format(getString(R.string.cases_confirmed), report.data.confirmedCases)
        casesDeathText.text = String.format(getString(R.string.deaths_confirmed), report.data.deathsCases)
    }
}


