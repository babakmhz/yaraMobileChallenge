package com.android.babakmhz.yaramobilechallenge.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.babakmhz.yaramobilechallenge.data.model.MovieWithRatings
import com.android.babakmhz.yaramobilechallenge.data.model.Search
import com.android.babakmhz.yaramobilechallenge.utils.AppLogger
import com.android.babakmhz.yaramobilechallenge.utils.LiveDataWrapper
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

class MainViewModel(
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val mainUseCase: MainUseCase
) : ViewModel(), KoinComponent {


    private var _currentMovie = MutableLiveData<Search>()
    val currentMovie: LiveData<Search> = _currentMovie

    private var _currentFragment = MutableLiveData<Fragment>()
    val currentFragment: LiveData<Fragment> = _currentFragment

    private var _moviesWrapper = MutableLiveData<LiveDataWrapper<List<Search>>>()
    val moviesWrapper: LiveData<LiveDataWrapper<List<Search>>> = _moviesWrapper

    private var _movieInDetailWrapper = MutableLiveData<LiveDataWrapper<MovieWithRatings>>()
    val movieInDetailWrapper: LiveData<LiveDataWrapper<MovieWithRatings>> = _movieInDetailWrapper

    private val job = SupervisorJob()
    private val mUiScope = CoroutineScope(mainDispatcher + job)


    fun getMovies() {
        _moviesWrapper.value = LiveDataWrapper.loading()
        mUiScope.launch {
            try {
                val response = mainUseCase.getMovies()
                if (response != null) {
                    _moviesWrapper.postValue(LiveDataWrapper.success(response))
                } else {
                    _moviesWrapper.postValue(LiveDataWrapper.error(Exception()))
                }
            } catch (e: Exception) {
                AppLogger.i("Exception in getting movies: %s", e.toString())
                _moviesWrapper.postValue(LiveDataWrapper.error(e))
            }
        }
    }


    fun getMovieInDetail(imdbId: String = currentMovie.value?.imdbId!!) {
        _movieInDetailWrapper.value = LiveDataWrapper.loading()
        mUiScope.launch {
            try {
                val response = mainUseCase.getMovieInDetail(imdbId)
                if (response != null) {
                    _movieInDetailWrapper.postValue(LiveDataWrapper.success(response))
                } else
                    _movieInDetailWrapper.postValue(LiveDataWrapper.error(Exception()))
            } catch (e: Exception) {
                AppLogger.e(e, "throwable")
                _movieInDetailWrapper.postValue(LiveDataWrapper.error(e))
            }
        }
    }

    fun setCurrentFragment(fragment: Fragment) {
        _currentFragment.value = fragment
    }

    fun setCurrentMovieInDetail(movie: MovieWithRatings) {
        _movieInDetailWrapper.value = LiveDataWrapper.success(movie)
    }

    fun getCurrentFragment(): Fragment? {
        return _currentFragment.value
    }

    fun onMovieClicked(currentMovie: Search, fragment: Fragment) {
        _currentMovie.value = currentMovie
        _currentFragment.value = fragment
    }


    object PosterBindingAdapter {
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