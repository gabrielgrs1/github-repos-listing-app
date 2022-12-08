package com.gabrielgrs2.listrepos.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gabrielgrs2.listrepos.core.platform.BaseViewModel
import com.gabrielgrs2.listrepos.core.utils.Result
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getSearchRepositories: GetSearchRepositoriesUseCase
) : BaseViewModel() {

    private var _state = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState> = _state

    private var _event = MutableLiveData<HomeViewEvent>()
    val viewEvent: LiveData<HomeViewEvent> = _event

    fun getSearchRepositories(page: String) {
        viewModelScope.launch {
            _state.value = HomeViewState.IsLoading(true)
            when (val result = getSearchRepositories.execute(page)) {
                is Result.Success -> {
                    result.data.let { search ->
                        _event.value = HomeViewEvent.OnSearchSuccess(
                            search = search
                        )
                    }
                    _state.value = HomeViewState.IsLoading(false)

                }

                is Result.Error -> {
                    _event.value = HomeViewEvent.OnSearchFailed()
                    _state.value = HomeViewState.IsLoading(false)
                }
            }
        }
    }
}
