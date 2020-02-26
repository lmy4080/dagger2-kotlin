package com.lmy.dagger2_kotlin.di

import com.lmy.dagger2_kotlin.di.auth.AuthModule
import com.lmy.dagger2_kotlin.di.auth.AuthScope
import com.lmy.dagger2_kotlin.di.auth.AuthViewModelsModule
import com.lmy.dagger2_kotlin.di.main.MainFragmentBuildersModule
import com.lmy.dagger2_kotlin.di.main.MainModule
import com.lmy.dagger2_kotlin.di.main.MainScope
import com.lmy.dagger2_kotlin.di.main.MainViewModelsModule
import com.lmy.dagger2_kotlin.ui.auth.AuthActivity
import com.lmy.dagger2_kotlin.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelsModule::class,
            AuthModule::class
        ]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}