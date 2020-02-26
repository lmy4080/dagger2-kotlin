package com.lmy.dagger2_kotlin.di

import androidx.lifecycle.ViewModelProvider
import com.lmy.dagger2_kotlin.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}