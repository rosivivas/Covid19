package com.rosario.covid19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rosario.covid19.R
import com.rosario.covid19.databinding.ActivityMainBinding
import com.rosario.covid19.viewModel.HomeViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
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
        vieweObservers()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = this
    }

    private fun prepareElements() {
        bt_select_date.setOnClickListener(this)
    }

    private fun vieweObservers(){

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_select_date -> {
                homeViewModel.getReport("2020-04-15")
            }
        }
    }
}
