package com.lmy.dagger2_kotlin.di.auth

import com.lmy.dagger2_kotlin.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideAuthApi(retrofit: Retrofit)
        = retrofit.create<AuthApi>(AuthApi::class.java)
}