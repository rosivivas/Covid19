package com.rosario.covid19.di.module

import com.rosario.covid19.domain.ReportUseCase
import com.rosario.covid19.viewModel.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun homeViewModel(useCase: ReportUseCase) = HomeViewModel(useCase)
}