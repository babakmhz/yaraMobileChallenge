package com.android.babakmhz.yaramobilechallenge.utils

import androidx.multidex.MultiDexApplication
import com.android.babakmhz.yaramobilechallenge.BuildConfig
import com.android.babakmhz.yaramobilechallenge.di.*
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
                    mainFragment,
                    detailsFragment,
                    useCaseDependency
                )
            )
        }
    }

}