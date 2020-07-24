package com.android.babakmhz.yaramobilechallenge

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.babakmhz.yaramobilechallenge.MainUseCase
import com.android.babakmhz.yaramobilechallenge.utils.LiveDataWrapper
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.KoinComponent

class MainViewModel(
    mainDispatcher: CoroutineDispatcher,
    private val mainUseCase: MainUseCase
) : ViewModel(), KoinComponent {

    private var _categoryTitle = MutableLiveData<String>()
    val categoryTitle: LiveData<String> = _categoryTitle

    private var _mainFragment = MutableLiveData<Boolean>()
    val mainFragment: LiveData<Boolean> = _mainFragment

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _currentFragment = MutableLiveData<Fragment>()
    val currentFragment: LiveData<Fragment> = _currentFragment

    private var _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)


    init {
        this._mainFragment.value = false
        this._currentFragment.value = null
    }


    fun setCurrentFragment(fragment: Fragment) {
        _currentFragment.value = fragment
    }

    fun getCurrentFragment(): Fragment? {
        return _currentFragment.value
    }

//    fun onCategoryClicked(category: Category, fragment: Fragment) {
//        _currentCategory.value = category
//        _selectedCategory.value = category.categories
//        _currentFragment.value = fragment
//        _categoryTitle.value = CommonUtils.getCategoryTitle(category.title)
//    }


    object dataBindingAdapter {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setDetailHeaderImage(imageView: ImageView, url: String?) {
            Glide.with(imageView).load(url).into(imageView)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}