package com.gabrielgrs2.listrepos.core.di

import com.gabrielgrs2.listrepos.data.repository.SearchRepository
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import com.gabrielgrs2.listrepos.presentation.home.HomeViewModel
import com.gabrielgrs2.listrepos.core.network.provideApi
import com.gabrielgrs2.listrepos.core.network.provideRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single<ISearchRepository> { SearchRepository(get()) }
}

val apiModule = module {
    factory { provideApi(get()) }
    single { provideRetrofit() }
}

val useCaseModule = module {
    factory { GetSearchRepositoriesUseCase(get()) }
}
