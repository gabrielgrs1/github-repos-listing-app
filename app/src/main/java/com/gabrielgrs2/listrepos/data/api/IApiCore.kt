package com.gabrielgrs2.listrepos.data.api

import com.gabrielgrs2.listrepos.core.utils.Constants
import com.gabrielgrs2.listrepos.data.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiCore {
    @GET(Constants.Router.SEARCH_REPOS)
    suspend fun getSearchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("page") page: String
    ): SearchDto
}
