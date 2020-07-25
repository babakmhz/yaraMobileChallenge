package com.android.babakmhz.yaramobilechallenge.di

import com.android.babakmhz.yaramobilechallenge.data.network.ApiService
import org.koin.dsl.module

val appApiService = module {
    factory { ApiService() }
}