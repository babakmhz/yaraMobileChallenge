package com.android.babakmhz.yaramobilechallenge.di

import com.android.babakmhz.yaramobilechallenge.ui.fragment.DetailsFragment
import com.android.babakmhz.yaramobilechallenge.ui.fragment.MainFragment
import org.koin.dsl.module

val detailsFragment = module {
    factory { DetailsFragment() }

}

val mainFragment = module {
    factory { MainFragment() }
}