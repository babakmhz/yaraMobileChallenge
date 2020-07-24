package com.android.babakmhz.yaramobilechallenge


import com.android.babakmhz.yaramobilechallenge.data.ApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainUseCase : KoinComponent {

    private val appApiService: ApiService by inject()
//    private val db: Db by inject()


}