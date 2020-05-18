package com.rosario.covid19.di.module

import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Module to provide view model class
 */
@Module
class ViewModelModule {
    @Provides
    fun homeViewModel(useCase: ReportUseCase) =
        HomeViewModel(useCase)
}