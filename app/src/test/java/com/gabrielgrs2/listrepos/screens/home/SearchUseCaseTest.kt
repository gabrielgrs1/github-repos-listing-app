package com.gabrielgrs2.listrepos.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.data.repository.SearchPagingSource
import com.gabrielgrs2.listrepos.data.repository.SearchRepository
import com.gabrielgrs2.listrepos.di.configureTestAppComponent
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class SearchUseCaseTest : BaseUTTest() {

    private lateinit var searchUseCase: GetSearchRepositoriesUseCase
    val searchService: ISearchService = mockk(relaxed = true)
    val searchPagingSource = SearchPagingSource(searchService)
    val searchRepository = SearchRepository(searchPagingSource)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun start() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun `test search use case returns expected value`() = runBlocking {

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        searchUseCase = GetSearchRepositoriesUseCase(searchRepository)

        val dataReceived = searchUseCase.execute()

        assertNotNull(dataReceived)
    }
}