package com.ekenya.rnd.dashboard.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.dashboard.ui.HomeFragment
import com.ekenya.rnd.dashboard.ui.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
@Module
abstract class DashboardFragmentModule {
    @Module
    abstract class HomeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

        @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
        abstract fun contributeAllAssetsFragment(): HomeFragment

    }
}