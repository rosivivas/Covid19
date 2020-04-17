package com.rosario.covid19.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
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
import java.util.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding


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

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
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
                homeViewModel.getReport(Util().dateFormat(calendar))
            },
            yearCal,
            monthCal,
            dayCal
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - (24 * 60 * 60 * 1000)
        datePickerDialog.show()

    }

    private fun viewObservers() {
        homeViewModel.error.observe(this, Observer {
            if (it.toLowerCase().contains("error"))
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        })
    }

}


