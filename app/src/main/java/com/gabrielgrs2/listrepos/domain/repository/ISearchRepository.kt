package com.gabrielgrs2.listrepos.domain.repository

import com.gabrielgrs2.listrepos.core.utils.Result
import com.gabrielgrs2.listrepos.domain.model.Search

interface ISearchRepository {
    suspend fun getSearchRepositories(page: String): Result<Search>
}