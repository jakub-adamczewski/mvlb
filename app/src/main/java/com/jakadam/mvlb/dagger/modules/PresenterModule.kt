package com.jakadam.mvlb.dagger.modules

import com.jakadam.mvlb.network.NetworkConnectionManager
import com.jakadam.mvlb.view.MainActivityPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    fun provideMainActivityPresenter(networkConnectionManager: NetworkConnectionManager): MainActivityPresenter = MainActivityPresenter(networkConnectionManager)


}