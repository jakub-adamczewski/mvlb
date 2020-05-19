package com.jakadam.mvlb.view

import com.jakadam.mvlb.network.NetworkConnectionManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.newFixedThreadPoolContext
import javax.inject.Inject

class MainActivityPresenter @Inject constructor(private val networkConnectionManager: NetworkConnectionManager) {

    val networkStatusObservable: Observable<Boolean> = networkConnectionManager.networkStatusObservable

    fun activityDestroyed(){
        networkConnectionManager.unregisterNetworkCallback()
    }

}