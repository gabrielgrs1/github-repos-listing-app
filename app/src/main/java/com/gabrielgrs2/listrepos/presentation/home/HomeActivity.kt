package com.gabrielgrs2.listrepos.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gabrielgrs2.listrepos.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding

    private val repositoriesAdapter = RepositoriesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewState()
        if (savedInstanceState == null) {
            lifecycleScope.launch {
                homeViewModel.onSuspendedEvent(Event.ScreenLoad)
            }
        }
        initAdapter()
        binding.repositoryRefreshSrl.setOnRefreshListener(this)
    }

    private fun initAdapter() {
        binding.repositoryListRv.adapter = repositoriesAdapter
    }


    private fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeViewState() {
        homeViewModel.obtainState.observe(this@HomeActivity) {
            render(it)
        }
    }

    private fun render(state: HomeViewState) {
        binding.repositoryRefreshSrl.isRefreshing = false
        state.loadingStateVisibility?.let { binding.loadingIv.visibility = it }
        lifecycleScope.launch {
            state.page?.let { repositoriesAdapter.submitData(it) }
        }
    }

    override fun onRefresh() {
        lifecycleScope.launch {
            homeViewModel.onSuspendedEvent(Event.ScreenLoad)
        }
    }
}