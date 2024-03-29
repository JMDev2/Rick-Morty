package com.ekenya.rnd.dashboard.di

import com.ekenya.rnd.baseapp.di.AppComponent
import com.ekenya.rnd.baseapp.di.ModuleScope
import com.ekenya.rnd.baseapp.di.injectables.ViewModelModule
import com.ekenya.rnd.dashboard.di.injectables.DashboardActivityModule
import com.ekenya.rnd.dashboard.di.injectables.DashboardFragmentModule
import com.ekenya.rnd.dashboard.di.injectables.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@ModuleScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        DashboardActivityModule::class,
        DashboardFragmentModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface DashboardComponent {
    fun inject(injector: DashboardInjector)
}