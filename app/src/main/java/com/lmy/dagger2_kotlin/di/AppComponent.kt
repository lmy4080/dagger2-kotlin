package com.lmy.dagger2_kotlin.di

import android.app.Application
import com.lmy.dagger2_kotlin.BaseApplication
import com.lmy.dagger2_kotlin.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent: AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {

        // AppComponent 가 생성될 때 Application 인스턴스를 내부에 가지게 해줌
        // ex) val application: Application
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}