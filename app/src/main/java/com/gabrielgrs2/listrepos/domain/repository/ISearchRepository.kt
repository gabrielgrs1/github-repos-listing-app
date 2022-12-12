package com.gabrielgrs2.listrepos.domain.repository

import androidx.paging.PagingData
import com.gabrielgrs2.listrepos.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun getSearchRepositories(): Flow<PagingData<Repository>>
}