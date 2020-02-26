package com.lmy.dagger2_kotlin.di.main

import androidx.lifecycle.ViewModel
import com.lmy.dagger2_kotlin.di.ViewModelKey
import com.lmy.dagger2_kotlin.ui.main.posts.PostsViewModel
import com.lmy.dagger2_kotlin.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap // 매핑
    @ViewModelKey(ProfileViewModel::class) // Key가 'AuthViewModel.class'인 곳에 매핑
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel
}