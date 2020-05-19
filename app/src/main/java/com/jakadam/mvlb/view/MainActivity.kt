package com.jakadam.mvlb.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.jakadam.mvlb.MvlbApp
import com.jakadam.mvlb.R
import com.jakadam.mvlb.api.ApiService
import com.jakadam.mvlb.dagger.modules.UiScheduler
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @UiScheduler
    @Inject
    lateinit var uiScheduler: Scheduler

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var presenter: MainActivityPresenter

    companion object {
        const val TAG = "MainActivity12345"
    }

    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as MvlbApp).component.inject(this)

        subscriptions.addAll(
            presenter.networkStatusObservable
                .observeOn(uiScheduler)
                .subscribe {
                    noInternetBanner.visibility = if (it) View.GONE else View.VISIBLE
                }
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroyed()
        subscriptions.dispose()
    }
}

