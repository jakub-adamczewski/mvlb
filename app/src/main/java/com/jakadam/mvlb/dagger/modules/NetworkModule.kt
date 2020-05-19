package com.jakadam.mvlb.dagger.modules

import android.content.Context
import com.jakadam.mvlb.network.NetworkConnectionManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideNetworkConnectionManager(context: Context): NetworkConnectionManager = NetworkConnectionManager(context)


}