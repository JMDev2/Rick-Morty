package com.ekenya.rnd.dashboard.di.injectables

import androidx.lifecycle.ViewModel
import com.ekenya.rnd.baseapp.di.ViewModelKey
import com.ekenya.rnd.dashboard.CharacterFragment
import com.ekenya.rnd.dashboard.HomeFragment
import com.ekenya.rnd.dashboard.ui.EpisodesFragment
import com.ekenya.rnd.dashboard.ui.HomeViewModel
import com.ekenya.rnd.dashboard.ui.LocationsFragment
import com.ekenya.rnd.dashboard.ui.SettingsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
@Module
abstract class DashboardFragmentModule {


    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeCharacterFragment(): CharacterFragment

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeepisodeFragment(): EpisodesFragment

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeLocationFragment(): LocationsFragment

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun contributeSettingsFragment(): SettingsFragment

    @Module
    abstract class HomeFragmentModule {
        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
    }



}