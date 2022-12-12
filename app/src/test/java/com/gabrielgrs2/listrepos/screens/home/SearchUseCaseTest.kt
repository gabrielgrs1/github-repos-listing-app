package com.gabrielgrs2.listrepos.screens.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.di.configureTestAppComponent
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class SearchUseCaseTest : BaseUTTest() {

    private lateinit var mLoginUseCase: GetSearchRepositoriesUseCase
    val searchRepository: ISearchRepository by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun start() {
        super.setUp()
        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun test_login_use_case_returns_expected_value() = runBlocking {

        mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)
        mLoginUseCase = GetSearchRepositoriesUseCase(searchRepository)

        val dataReceived = mLoginUseCase.execute()

        assertNotNull(dataReceived)
    }
}