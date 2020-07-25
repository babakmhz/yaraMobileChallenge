package com.android.babakmhz.yaramobilechallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.babakmhz.yaramobilechallenge.R
import com.android.babakmhz.yaramobilechallenge.data.model.Search
import com.android.babakmhz.yaramobilechallenge.databinding.FragmentMainBinding
import com.android.babakmhz.yaramobilechallenge.ui.ItemsRecyclerAdapter
import com.android.babakmhz.yaramobilechallenge.ui.MainUseCase
import com.android.babakmhz.yaramobilechallenge.ui.MainViewModel
import com.android.babakmhz.yaramobilechallenge.ui.callBack
import com.android.babakmhz.yaramobilechallenge.utils.LiveDataWrapper
import com.android.babakmhz.yaramobilechallenge.utils.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class MainFragment : Fragment(), KoinComponent, callBack {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private val mainUseCase: MainUseCase by inject()
    private val mainViewModelFactory = MainViewModelFactory(
        Dispatchers.Main,
        mainUseCase
    )
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentMainBinding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main, container, false
            )
        viewModel = activity?.run {
            ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        }!!
        fragmentMainBinding.viewModel = viewModel
        fragmentMainBinding.executePendingBindings()
        viewModel.getMovies()
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.moviesWrapper.observe(viewLifecycleOwner, liveDataObserver)
        bt_tryAgain.setOnClickListener {
            viewModel.getMovies()
        }
    }

    private val liveDataObserver = Observer<LiveDataWrapper<List<Search>>> {
        when (it?.responseRESPONSESTATUS) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                recycler_items.visibility = View.GONE
                bt_tryAgain.visibility = View.GONE
                text_loading.text = activity?.getString(R.string.loading_movies)
                progress.visibility = View.VISIBLE
                text_loading.visibility = View.VISIBLE
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                recycler_items.visibility = View.GONE
                progress.visibility = View.GONE
                text_loading.visibility = View.VISIBLE
                bt_tryAgain.visibility = View.VISIBLE
                text_loading.text = activity?.getString(R.string.error)
            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API

                recycler_items.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recycler_items.adapter = ItemsRecyclerAdapter(
                    context!!,
                    it.response as ArrayList<Search>, this
                )

                progress.visibility = View.GONE
                text_loading.visibility = View.GONE
                bt_tryAgain.visibility = View.GONE
                recycler_items.visibility = View.VISIBLE
            }
        }
    }

    override fun onItemClicked(movie: Search) {
        viewModel.onMovieClicked(movie, DetailsFragment.newInstance())
    }
}