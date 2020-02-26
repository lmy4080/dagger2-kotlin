package com.lmy.dagger2_kotlin.di.main

import com.lmy.dagger2_kotlin.network.main.MainApi
import com.lmy.dagger2_kotlin.ui.main.posts.PostsRecyclerAdapter
import com.lmy.dagger2_kotlin.util.VerticalSpacingItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideSpacingItemDecoration(): VerticalSpacingItemDecoration
        = VerticalSpacingItemDecoration(15)

    @MainScope
    @Provides
    fun provideRecyclerAdapter(): PostsRecyclerAdapter
        = PostsRecyclerAdapter()

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi
        = retrofit.create<MainApi>(MainApi::class.java)
}