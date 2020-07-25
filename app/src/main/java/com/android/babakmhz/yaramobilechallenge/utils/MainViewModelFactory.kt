package com.android.babakmhz.yaramobilechallenge.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.babakmhz.yaramobilechallenge.ui.MainViewModel
import com.android.babakmhz.yaramobilechallenge.ui.MainUseCase
import kotlinx.coroutines.CoroutineDispatcher


class MainViewModelFactory constructor(
    private val mainDispatcher: CoroutineDispatcher
    , private val useCase: MainUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(
                mainDispatcher = mainDispatcher,
                mainUseCase = useCase
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel Not configured") as Throwable
        }
    }
}