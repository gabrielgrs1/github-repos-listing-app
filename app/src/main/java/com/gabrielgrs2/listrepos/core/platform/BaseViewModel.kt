package com.gabrielgrs2.listrepos.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabrielgrs2.listrepos.domain.model.Lce
import kotlinx.coroutines.Job

abstract class BaseViewModel<ViewState : BaseViewState,
        Event : BaseEvent,
        Result : BaseResult>(initialState: ViewState) : ViewModel() {

    internal val viewStateLD = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> get() = viewStateLD

    var loadJob: Job? = null

    suspend fun onSuspendedEvent(event: Event) {
        suspendEventToResult(event)
    }

    abstract fun eventToResult(event: Event)

    abstract suspend fun suspendEventToResult(event: Event)

    abstract fun resultToViewState(result: Lce<Result>)

}

interface BaseViewState

interface BaseEvent

interface BaseResult
