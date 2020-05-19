package com.jakadam.mvlb.dagger

import com.jakadam.mvlb.dagger.modules.ApiModule
import com.jakadam.mvlb.dagger.modules.AppModule
import com.jakadam.mvlb.dagger.modules.NetworkModule
import com.jakadam.mvlb.dagger.modules.SchedulersModule
import com.jakadam.mvlb.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ApiModule::class,
        SchedulersModule::class
    ]
)
interface AppComponent {
    fun inject(target: MainActivity)
}