package com.gabrielgrs2.listrepos.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gabrielgrs2.listrepos.domain.model.Owner
import com.gabrielgrs2.listrepos.domain.model.Repository
import retrofit2.HttpException
import java.io.IOException

class FakeSearchPagingSource : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val position = params.key ?: 1
        return try {
            val repositories = listOf(
                Repository(1, Owner("", "teste"), "nome", 1, 1),
                Repository(2, Owner("", "teste2"), "nome2", 2, 2)
            )
            LoadResult.Page(
                data = repositories,
                prevKey = if (position == 1) null else position,
                nextKey = if (repositories.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        /* Do nothing */
        return -1
    }
}