package com.rosario.covid19.di.module

import android.content.Context
import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.viewModel.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Module to provide view model class
 */
@Module
class ViewModelModule {
    @Provides
    fun homeViewModel(useCase: ReportUseCase, context: Context) = HomeViewModel(useCase, context)
}