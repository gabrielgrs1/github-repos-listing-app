package com.gabrielgrs2.listrepos.presentation.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielgrs2.listrepos.core.platform.BaseActivity
import com.gabrielgrs2.listrepos.databinding.ActivityHomeBinding
import com.gabrielgrs2.listrepos.domain.model.Search
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    override fun init() {
        initViewBinding()
        observeViewModelEvents()
        observeViewModelStates()
        initAdapter()
        homeViewModel.getSearchRepositories("1")
    }

    private fun initAdapter() {
        binding.repositoryListRv.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        repositoriesAdapter = RepositoriesAdapter(listOf(), this)
        binding.repositoryListRv.adapter = repositoriesAdapter
    }

    private fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewModelEvents() {
        homeViewModel.viewEvent.observe(this@HomeActivity) {
            when (it) {
                is HomeViewEvent.OnSearchSuccess -> handleSearchSuccess(it.search)
                is HomeViewEvent.OnSearchFailed -> showError()
            }
        }
    }

    private fun showError() {
        // TODO Show toast error
    }

    private fun handleSearchSuccess(search: Search) {
        repositoriesAdapter.repositories = search.repositories
        repositoriesAdapter.notifyDataSetChanged()
    }

    private fun observeViewModelStates() {
        homeViewModel.viewState.observe(this@HomeActivity) {
            when (it) {
                is HomeViewState.IsLoading ->
                    showLoading(it.isLoading)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingIv.isVisible = isLoading
    }
}