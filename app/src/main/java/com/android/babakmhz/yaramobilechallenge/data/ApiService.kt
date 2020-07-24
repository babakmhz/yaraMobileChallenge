package com.android.babakmhz.yaramobilechallenge.data

import org.koin.core.KoinComponent
import org.koin.core.inject
import javax.inject.Inject

class ApiService : KoinComponent {

    private val apiHelper: ApiHelper by inject()
}