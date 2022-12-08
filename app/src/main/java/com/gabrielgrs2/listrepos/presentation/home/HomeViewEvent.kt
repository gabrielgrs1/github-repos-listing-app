package com.gabrielgrs2.listrepos.presentation.home

import com.gabrielgrs2.listrepos.domain.model.Search

open class HomeViewEvent {
    data class OnSearchSuccess(var search: Search) : HomeViewEvent()
    class OnSearchFailed : HomeViewEvent()
}
