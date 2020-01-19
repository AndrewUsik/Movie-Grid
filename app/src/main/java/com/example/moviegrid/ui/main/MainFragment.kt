package com.example.moviegrid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviegrid.R
import com.example.moviegrid.base.BaseFragment
import com.example.moviegrid.base.list.AdapterClickListener
import com.example.moviegrid.base.list.EndlessRecyclerViewScrollListener
import com.example.moviegrid.base.list.decorator.GridSpacesItemDecoration
import com.example.moviegrid.databinding.FragmentMainBinding
import com.example.moviegrid.domain.entity.Movie
import com.example.moviegrid.extentions.setupSwipeToRefresh
import com.example.moviegrid.ui.deteil.DetailFragment
import com.example.moviegrid.ui.main.list.MovieListAdapter


open class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by bindViewModel()
    private val listAdapter = MovieListAdapter()

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        initAdapter()
        initToolbar()
        initSwipeToRefresh()

        if (savedInstanceState == null) {
            viewModel.getMovies(1)
        } else {
            gridLayoutManager.onRestoreInstanceState(
                savedInstanceState.getParcelable(
                    EXTRA_LIST_STATE
                )
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_LIST_STATE, gridLayoutManager.onSaveInstanceState())
    }

    private fun initToolbar() {
        binding.toolbarContent.run {
            titleText.text = getString(R.string.main_fragment_toolbar_title)
            initToolbar(toolbar)
        }
    }

    private fun initSwipeToRefresh() {
        binding.run {
            setupSwipeToRefresh(swipeToRefresh) {
                listAdapter.clearData()
                viewModel.getMovies(1)
            }
        }
    }

    private fun initAdapter() {
        gridLayoutManager = GridLayoutManager(context, 2)
        val gridItemDecorator = GridSpacesItemDecoration()
        gridItemDecorator.space = 32

        val scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.getMovies(page)
            }
        }

        binding.moviesRecyclerView.run {
            layoutManager = gridLayoutManager
            adapter = listAdapter
            addItemDecoration(gridItemDecorator)
            addOnScrollListener(scrollListener)
        }

        listAdapter.setClickListener(object : AdapterClickListener<Movie> {
            override fun onItemClick(data: Movie) {
                replaceFragmentWithBackStack(DetailFragment.newInstance(data))
            }
        })
    }

    private fun initLiveData() {
        viewModel.movieListLiveData.observe(this, Observer {
            if (listAdapter.itemCount == 0) {
                listAdapter.setData(it)
            } else {
                listAdapter.addData(it)
            }
        })

        viewModel.showProgressLiveData.observe(this, Observer { showProgress ->
            binding.swipeToRefresh.isRefreshing = showProgress
        })
    }

    companion object {
        private const val EXTRA_LIST_STATE = "extra_list_state"
        fun newInstance() = MainFragment()
    }
}