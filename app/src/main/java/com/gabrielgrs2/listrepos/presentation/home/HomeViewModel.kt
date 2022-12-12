package com.gabrielgrs2.listrepos.presentation.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.gabrielgrs2.listrepos.core.platform.BaseViewModel
import com.gabrielgrs2.listrepos.domain.model.Lce
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getSearchRepositories: GetSearchRepositoriesUseCase,
) : BaseViewModel<HomeViewState, Event, Result>(HomeViewState()) {

    private var currentViewState = HomeViewState()
        set(value) {
            field = value
            viewStateLD.postValue(value)
        }

    val obtainState: LiveData<HomeViewState> = viewState

    private fun fetchData() {
        resultToViewState(Lce.Loading())
        getSearchRepositories()
    }


    override suspend fun suspendEventToResult(event: Event) {
        when (event) {
            is Event.ScreenLoad, Event.SwipeToRefreshEvent -> fetchData()
            is Event.LoadState -> {/* Do nothing */
            }
        }
    }

    private fun getSearchRepositories() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch(Dispatchers.IO) {
            getSearchRepositories.execute()
                .cachedIn(viewModelScope)
                .collect { results ->
                    resultToViewState(Lce.Content(Result.Content(results)))
                }
        }
    }

    override fun resultToViewState(result: Lce<Result>) {
        currentViewState = when (result) {
            is Lce.Loading -> {
                currentViewState.copy(
                    loadingStateVisibility = View.VISIBLE,
                    errorVisibility = View.GONE
                )
            }
            is Lce.Content -> {
                when (result.packet) {
                    is Result.Content ->
                        currentViewState.copy(
                            page = result.packet.content,
                            loadingStateVisibility = View.GONE,
                            errorVisibility = View.GONE
                        )
                    else -> currentViewState.copy()
                }
            }
            is Lce.Error -> {
                when (result.packet) {
                    is Result.Error ->
                        currentViewState.copy(
                            errorVisibility = View.VISIBLE,
                            errorMessage = result.packet.errorMessage,
                            loadingStateVisibility = View.GONE
                        )
                    else -> currentViewState.copy()
                }
            }
        }
    }

    override fun eventToResult(event: Event) {
        when (event) {
            is Event.LoadState -> onLoadState(event.state)
            Event.ScreenLoad -> {/* Do nothing */
            }
            Event.SwipeToRefreshEvent -> {/* Do nothing */
            }
        }
    }

    private fun onLoadState(state: CombinedLoadStates) {
        when (state.source.refresh) {
            is LoadState.Error -> {
                val errorState = state.source.append as? LoadState.Error
                    ?: state.source.prepend as? LoadState.Error
                    ?: state.append as? LoadState.Error
                    ?: state.prepend as? LoadState.Error
                errorState?.let {
                    resultToViewState(Lce.Error(Result.Error(errorMessage = errorState.error.localizedMessage)))
                }
            }
            is LoadState.Loading -> resultToViewState(Lce.Loading())
            is LoadState.NotLoading -> {/* Do nothing */
            }
        }

    }
}
