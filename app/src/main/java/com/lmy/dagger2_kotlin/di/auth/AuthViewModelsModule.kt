package com.lmy.dagger2_kotlin.di.auth

import androidx.lifecycle.ViewModel
import com.lmy.dagger2_kotlin.di.ViewModelKey
import com.lmy.dagger2_kotlin.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap // 매핑
    @ViewModelKey(AuthViewModel::class) // Key가 'AuthViewModel.class'인 곳에 매핑
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}