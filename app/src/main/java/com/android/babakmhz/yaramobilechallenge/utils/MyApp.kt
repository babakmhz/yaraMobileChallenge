package com.android.babakmhz.yaramobilechallenge.utils

import android.app.Application
import com.android.babakmhz.yaramobilechallenge.BuildConfig
import com.android.babakmhz.yaramobilechallenge.di.networkDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            AppLogger.init()

        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkDependency
                )
            )
        }
    }

}