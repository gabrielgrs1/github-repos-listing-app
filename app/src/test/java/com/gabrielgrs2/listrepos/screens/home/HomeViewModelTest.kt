package com.gabrielgrs2.listrepos.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.di.configureTestAppComponent
import com.gabrielgrs2.listrepos.domain.model.Owner
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import com.gabrielgrs2.listrepos.presentation.home.HomeViewModel
import com.gabrielgrs2.listrepos.screens.utils.FakeSearchPagingSource
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    var searchUseCase: GetSearchRepositoriesUseCase = mockk(relaxed = true)
    var homeViewModel = HomeViewModel(searchUseCase)

    @Before
    fun start() {
        super.setUp()
        MockKAnnotations.init(this)
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }
}