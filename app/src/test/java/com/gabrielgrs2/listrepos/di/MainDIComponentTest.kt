package com.gabrielgrs2.listrepos.di

import com.gabrielgrs2.listrepos.core.di.repositoryModule
import com.gabrielgrs2.listrepos.core.di.useCaseModule

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Usecase
 * 3) Repository
 */
fun configureTestAppComponent(baseApi: String) = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    useCaseModule,
    repositoryModule
)

