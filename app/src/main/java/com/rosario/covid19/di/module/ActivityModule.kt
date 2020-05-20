package com.rosario.covid19.di.module

import com.rosario.covid19.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to inject Activities
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun bindLoginActivity(): HomeActivity

}