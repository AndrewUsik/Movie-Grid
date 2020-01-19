package com.example.moviegrid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviegrid.core.di.ViewModelKey
import com.example.moviegrid.core.viewmodel.ViewModelFactory
import com.example.moviegrid.ui.deteil.DeteilViewModel
import com.example.moviegrid.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Binds
    @Singleton
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeteilViewModel::class)
    fun bindDeteilViewModel(viewModel: DeteilViewModel): ViewModel
}