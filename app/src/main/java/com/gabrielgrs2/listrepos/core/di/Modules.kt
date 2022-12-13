package com.gabrielgrs2.listrepos.core.di

import com.gabrielgrs2.listrepos.core.network.provideRetrofit
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.data.repository.SearchPagingSource
import com.gabrielgrs2.listrepos.data.repository.SearchRepository
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import com.gabrielgrs2.listrepos.domain.usecase.home.GetSearchRepositoriesUseCase
import com.gabrielgrs2.listrepos.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val pagingSource = module {
    factory { SearchPagingSource(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single<ISearchRepository> { SearchRepository(get()) }
}

val apiModule = module {
    factory { get<Retrofit>().create(ISearchService::class.java) }
    single { provideRetrofit() }
}

val useCaseModule = module {
    factory { GetSearchRepositoriesUseCase(get()) }
}
