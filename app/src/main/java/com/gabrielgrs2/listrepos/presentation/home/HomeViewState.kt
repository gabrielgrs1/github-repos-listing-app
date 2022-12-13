package com.gabrielgrs2.listrepos.presentation.home

import android.view.View
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.gabrielgrs2.listrepos.core.platform.BaseEvent
import com.gabrielgrs2.listrepos.core.platform.BaseResult
import com.gabrielgrs2.listrepos.core.platform.BaseViewState
import com.gabrielgrs2.listrepos.domain.model.Repository

data class HomeViewState(
    val page: PagingData<Repository>? = null,
    val errorMessageResource: Int? = null,
    val errorMessage: String? = null,
    val loadingStateVisibility: Int? = View.GONE,
    val errorVisibility: Int? = View.GONE
) : BaseViewState

sealed class Event : BaseEvent {
    object SwipeToRefreshEvent : Event()
    data class LoadState(val state: CombinedLoadStates) : Event()
    object ScreenLoad : Event()
}

sealed class Result : BaseResult {
    data class Error(val errorMessage: String?) : Result()
    data class Content(val content: PagingData<Repository>) : Result()
}