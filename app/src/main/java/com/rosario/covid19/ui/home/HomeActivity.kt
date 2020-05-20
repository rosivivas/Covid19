package com.rosario.covid19.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rosario.covid19.R
import com.rosario.covid19.databinding.ActivityMainBinding
import com.rosario.covid19.util.Status
import com.rosario.covid19.util.Util
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

/**
 * Handle UI for Home
 */
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding
    private val calendar = Calendar.getInstance()
    val util = Util()

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
        dateTitleText.text = util.getYesterdayDateUser(calendar)
        homeViewModel.getReport(util.getYesterdayDate(calendar))
    }

    private fun prepareElements() {
        buttonDisplayCalendar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonDisplayCalendar -> {
                util.showDatePicker(this) { dateServer, dateUser ->
                    homeViewModel.getReport(dateServer)
                    dateTitleText.text = dateUser
                }
            }
        }
    }

    private fun viewObservers() {
        homeViewModel.fetchReportViewDataLiveData.observe(this, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBarHome.visibility = View.GONE
                        errorText.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        progressBarHome.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = util.handleError(it.error!!,this)
                    }
                    Status.LOADING -> {
                        errorText.visibility = View.GONE
                        progressBarHome.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}


