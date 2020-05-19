package com.jakadam.mvlb.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import io.reactivex.subjects.PublishSubject
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables


class NetworkConnectionManager(val context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val cellularStatusSubject = PublishSubject.create<Boolean>()
    private val wifiStatusSubject = PublishSubject.create<Boolean>()

    val networkStatusObservable: Observable<Boolean> = Observables
        .combineLatest(cellularStatusSubject, wifiStatusSubject) { a, b ->
            a || b
        }

    private val cellularRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val wifiRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .build()

    private val cellularCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {
            super.onLost(network)
            cellularStatusSubject.onNext(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            cellularStatusSubject.onNext(false)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            cellularStatusSubject.onNext(true)
        }
    }

    private val wifiCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network) {
            super.onLost(network)
            wifiStatusSubject.onNext(false)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            wifiStatusSubject.onNext(false)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            wifiStatusSubject.onNext(true)
        }
    }

    init {
        connectivityManager.registerNetworkCallback(cellularRequest, cellularCallback)
        connectivityManager.registerNetworkCallback(wifiRequest, wifiCallback)
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(cellularCallback)
        connectivityManager.unregisterNetworkCallback(wifiCallback)
    }
}


//class NetworkConnectionManager(val context: Context) {
//
//    private val networkStatusSubject = BehaviorSubject.create<Boolean>()
//
//    val networkStatusObservable = networkStatusSubject
//
//    private val networkRequest = NetworkRequest.Builder()
//        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//        .build()
//
//    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
//        override fun onLost(network: Network) {
//            super.onLost(network)
//            networkStatusSubject.onNext(false)
//        }
//
//        override fun onUnavailable() {
//            super.onUnavailable()
//            networkStatusSubject.onNext(false)
//        }
//
//        override fun onAvailable(network: Network) {
//            super.onAvailable(network)
//            networkStatusSubject.onNext(true)
//        }
//    }
//
//    private val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//    init {
//        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
//    }
//
//    fun unregisterNetworkCallback() {
//        connectivityManager.unregisterNetworkCallback(networkCallback)
//    }
//}
