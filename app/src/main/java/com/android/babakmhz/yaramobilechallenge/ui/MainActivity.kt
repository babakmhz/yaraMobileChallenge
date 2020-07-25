package com.android.babakmhz.yaramobilechallenge.ui

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.babakmhz.yaramobilechallenge.R
import com.android.babakmhz.yaramobilechallenge.databinding.ActivityMainBinding
import com.android.babakmhz.yaramobilechallenge.ui.fragment.MainFragment
import com.android.babakmhz.yaramobilechallenge.utils.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    //please check this repo for my java clean MVP project and other skills in java
    // https://github.com/babakmhz/homefit_android

    //i couldn't write any tests due to lack of time but my testing skills are
    //available on https://github.com/babakmhz/cafebazar_challenge/tree/master/app/src/test/java/com/android/babakmhz/cafebazarchallenge

    private val useCase: MainUseCase by inject()
    private var backPressedOnce = false

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

    override fun onBackPressed() {
        if (backPressedOnce) {
            finish()
        }

        if (viewModel.currentFragment.value is MainFragment
        ) {
            showSnackBar(
                getString(R.string.press_back_again)
            )
            backPressedOnce = true
            Handler().postDelayed({
                backPressedOnce = false
            }, 1500)
        } else {
            viewModel.setCurrentFragment(MainFragment.newInstance())
        }

    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(
            container,
            message, Snackbar.LENGTH_LONG
        )
        val sbView = snackbar.view
        val textView = sbView
            .findViewById<View>(R.id.snackbar_text) as TextView
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite))
        snackbar.show()
    }

}