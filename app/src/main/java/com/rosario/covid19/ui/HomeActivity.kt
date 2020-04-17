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
import com.rosario.covid19.databinding.ActivityMainBinding
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
        tv_date.text = Util().getYesterdayDateUser(Calendar.getInstance())
        homeViewModel.getReport(Util().getYesterdayDate(Calendar.getInstance()))
    }

    private fun prepareElements() {
        bt_select_date.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_select_date -> {
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
                tv_date.text = Util().userFormatDate(calendar)
                selectedDate = Util().dateFormat(calendar)
                homeViewModel.getReport(Util().dateFormat(calendar))
            },
            yearCal,
            monthCal,
            dayCal
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()

    }

    private fun viewObservers() {
        homeViewModel.error.observe(this, Observer {
            Toast.makeText(this, Util().handleError(it, this), Toast.LENGTH_SHORT).show()
        })
    }

}


