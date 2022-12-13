package com.gabrielgrs2.listrepos.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.di.configureTestAppComponent
import com.gabrielgrs2.listrepos.domain.model.Owner
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import com.gabrielgrs2.listrepos.presentation.home.Event
import com.gabrielgrs2.listrepos.presentation.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
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
import kotlin.test.assertNull

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    var searchUseCase: GetSearchRepositoriesUseCase = mockk(relaxed = true)
    var homeViewModel = HomeViewModel(searchUseCase)
    val pagingSource = FakeSearchPagingSource()

    @Before
    fun start() {
        super.setUp()
        MockKAnnotations.init(this)
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `verify paging data is success when paging correctly loaded`() {
        runTest {
            val params: PagingSource.LoadParams.Refresh<Int> = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )

            val repositories = listOf(
                Repository(1, Owner("", "teste"), "nome", 1, 1),
                Repository(2, Owner("", "teste2"), "nome2", 2, 2)
            )

            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = repositories,
                    prevKey = null,
                    nextKey = repositories[1].id
                ), actual = pagingSource.load(params)
            )

        }
    }

//    @Test
//    fun `verify paging data is null when paging correctly didn't load correctly`() {
//        runTest {
//            val params: PagingSource.LoadParams.Refresh<Int> = PagingSource.LoadParams.Refresh(
//                key = 1,
//                loadSize = 0,
//                placeholdersEnabled = false
//            )
//
//            coEvery { pagingSource.load(params) } returns PagingSource.LoadResult.Page(
//                data = listOf(),
//                prevKey = 0,
//                nextKey = 1
//            )
//
//            every { searchUseCase.execute() } returns
//                    Pager(
//                        config = PagingConfig(
//                            pageSize = 30,
//                            enablePlaceholders = true
//                        ),
//                        pagingSourceFactory = { pagingSource }
//                    ).flow
//
//            homeViewModel.suspendEventToResult(Event.ScreenLoad)
//
//            assertNull(homeViewModel.obtainState.value?.page)
//        }
//    }
}