package com.gabrielgrs2.listrepos.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val service: ISearchService) : ISearchRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }

    override fun getSearchRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { SearchPagingSource(service) }
        ).flow
    }
}
