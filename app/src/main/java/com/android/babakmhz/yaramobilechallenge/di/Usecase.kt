package com.android.babakmhz.yaramobilechallenge.di

import com.android.babakmhz.yaramobilechallenge.ui.MainUseCase
import org.koin.dsl.module

val useCaseDependency = module {
    single {
        MainUseCase()
    }


}