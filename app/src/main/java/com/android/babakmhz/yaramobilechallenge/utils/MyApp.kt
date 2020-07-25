package com.android.babakmhz.yaramobilechallenge.utils

import androidx.multidex.MultiDexApplication
import com.android.babakmhz.yaramobilechallenge.BuildConfig
import com.android.babakmhz.yaramobilechallenge.di.appApiService
import com.android.babakmhz.yaramobilechallenge.di.databaseDependency
import com.android.babakmhz.yaramobilechallenge.di.networkDependency
import com.android.babakmhz.yaramobilechallenge.di.useCaseDependency
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            AppLogger.init()

        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    networkDependency,
                    appApiService,
                    databaseDependency,
                    useCaseDependency
                )
            )
        }
    }

}