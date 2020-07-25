package com.android.babakmhz.yaramobilechallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.babakmhz.yaramobilechallenge.R
import com.android.babakmhz.yaramobilechallenge.databinding.ActivityMainBinding
import com.android.babakmhz.yaramobilechallenge.ui.fragment.MainFragment
import com.android.babakmhz.yaramobilechallenge.utils.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    //please check this repo for my java clean MVP project and other skills in java
    // https://github.com/babakmhz/homefit_android


    private val useCase: MainUseCase by inject()
    private val mainFragment =
        MainFragment.newInstance()
    private val viewModelFactory = MainViewModelFactory(
        Dispatchers.Main
        , useCase
    )
    private lateinit var activityMainBinding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        activityMainBinding.viewModel = viewModel
        viewModel.currentFragment.observe(this, fragmentsObserver)
        activityMainBinding.executePendingBindings()
        viewModel.setCurrentFragment(mainFragment)
    }

    private val fragmentsObserver = Observer<Fragment> {
        if (it != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, it)
                .commitNow()

        }
    }
}