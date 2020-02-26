package com.lmy.dagger2_kotlin.di.main

import com.lmy.dagger2_kotlin.ui.main.posts.PostsFragment
import com.lmy.dagger2_kotlin.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributePostsFragment(): PostsFragment
}