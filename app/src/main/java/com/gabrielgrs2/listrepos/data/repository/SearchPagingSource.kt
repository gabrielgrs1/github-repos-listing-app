package com.gabrielgrs2.listrepos.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabrielgrs2.listrepos.core.utils.Constants.Numbers.SEARCH_STARTING_PAGE_INDEX
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.data.mapper.toSearchRepositories
import com.gabrielgrs2.listrepos.domain.model.Repository
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val service: ISearchService
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: SEARCH_STARTING_PAGE_INDEX
        return try {
            val response = service.getSearchRepositories(
                query = "language:kotlin",
                sort = "stars",
                page = position
            )
            val repositories = response.toSearchRepositories().repositories
            LoadResult.Page(
                data = repositories,
                prevKey = if (position == SEARCH_STARTING_PAGE_INDEX) null else position,
                nextKey = if (repositories.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: RuntimeException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        /* Do nothing */
        return -1
    }
}
