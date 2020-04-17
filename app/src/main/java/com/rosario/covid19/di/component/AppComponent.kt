package com.rosario.covid19.di.component

import android.app.Application
import com.rosario.covid19.App
import com.rosario.covid19.di.module.ActivityModule
import com.rosario.covid19.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


}