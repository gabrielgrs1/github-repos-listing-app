package com.gabrielgrs2.listrepos.domain.usecase.home

import com.gabrielgrs2.listrepos.core.utils.Result
import com.gabrielgrs2.listrepos.domain.model.Search
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository

open class GetSearchRepositoriesUseCase(private val searchRepository: ISearchRepository) {
    suspend fun execute(page: String): Result<Search> =
        searchRepository.getSearchRepositories(page)
}