package com.gabrielgrs2.listrepos.core.platform

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val viewModelJob = SupervisorJob()
    override var coroutineContext: CoroutineContext = Main + viewModelJob

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}
