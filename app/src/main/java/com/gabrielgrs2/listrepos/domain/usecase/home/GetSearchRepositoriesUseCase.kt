package com.gabrielgrs2.listrepos.domain.usecase.home

import androidx.paging.PagingData
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

open class GetSearchRepositoriesUseCase(private val searchRepository: ISearchRepository) {
    fun execute(): Flow<PagingData<Repository>> =
        searchRepository.getSearchRepositories()
}