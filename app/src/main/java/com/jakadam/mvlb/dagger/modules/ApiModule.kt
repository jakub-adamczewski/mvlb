package com.jakadam.mvlb.dagger.modules

import com.jakadam.mvlb.api.ApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {


    companion object {
        private const val NAME_BASE_URL = "mvlb_base_url"
    }

    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrlString(): String = "https://mvlb.pl/wp-json/"

    @Provides
    @Singleton
    fun provideRetrofit(@Named(NAME_BASE_URL) baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Reusable
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}