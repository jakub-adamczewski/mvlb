package com.jakadam.mvlb

import android.app.Application
import com.jakadam.mvlb.dagger.AppComponent
import com.jakadam.mvlb.dagger.DaggerAppComponent
import com.jakadam.mvlb.dagger.modules.AppModule

class MvlbApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = initDagger(this)
    }

    private fun initDagger(app: Application): AppComponent =
        DaggerAppComponent
            .builder()
            .appModule(AppModule(app))
            .build()
}