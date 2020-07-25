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
import com.android.babakmhz.yaramobilechallenge.data.model.MovieWithRatings
import com.android.babakmhz.yaramobilechallenge.databinding.FragmentDetailsBinding
import com.android.babakmhz.yaramobilechallenge.ui.MainUseCase
import com.android.babakmhz.yaramobilechallenge.ui.MainViewModel
import com.android.babakmhz.yaramobilechallenge.ui.ScoreRecyclerAdapter
import com.android.babakmhz.yaramobilechallenge.utils.LiveDataWrapper
import com.android.babakmhz.yaramobilechallenge.utils.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailsFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() =
            DetailsFragment()
    }

    private val mainUseCase: MainUseCase by inject()
    private val mainViewModelFactory = MainViewModelFactory(
        Dispatchers.Main,
        mainUseCase
    )
    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailsBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_details, container, false
            )
        viewModel = activity?.run {
            ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        }!!
        fragmentDetailsBinding.viewModel = viewModel
        fragmentDetailsBinding.executePendingBindings()
        return fragmentDetailsBinding.root
    }


    private val liveDataObserver = Observer<LiveDataWrapper<MovieWithRatings>> {
        when (it?.responseRESPONSESTATUS) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                recycler_score.visibility = View.GONE
                poster.visibility = View.GONE
                tryAgain.visibility = View.GONE
                progressbar.visibility = View.VISIBLE
                text_loading_.visibility = View.VISIBLE
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
                recycler_score.visibility = View.GONE
                progressbar.visibility = View.GONE
                text_loading_.visibility = View.VISIBLE
                tryAgain.visibility = View.VISIBLE
                text_loading_.text = activity?.getString(R.string.error)
            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                // Data from API
                if (it.response?.ratings?.isNotEmpty()!!)
                    recycler_score.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                recycler_items.adapter = ScoreRecyclerAdapter(
                    context!!,
                    it.response.ratings
                )

                progressbar.visibility = View.GONE
                text_loading_.visibility = View.GONE
                tryAgain.visibility = View.GONE
                recycler_score.visibility = View.VISIBLE
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieInDetailWrapper.observe(viewLifecycleOwner, liveDataObserver)
    }
}