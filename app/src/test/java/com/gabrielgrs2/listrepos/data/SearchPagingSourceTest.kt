package com.gabrielgrs2.listrepos.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.data.api.ISearchService
import com.gabrielgrs2.listrepos.data.dto.OwnerDto
import com.gabrielgrs2.listrepos.data.dto.RepositoryDto
import com.gabrielgrs2.listrepos.data.dto.SearchDto
import com.gabrielgrs2.listrepos.data.mapper.toSearchRepositories
import com.gabrielgrs2.listrepos.data.repository.SearchPagingSource
import com.gabrielgrs2.listrepos.domain.model.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class SearchPagingSourceTest : BaseUTTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val searchService: ISearchService = mockk(relaxed = true)
    val pagingSource = SearchPagingSource(searchService)

    @Test
    fun `verify paging data is success when paging correctly loaded`() {
        runTest {
            val searchDto = SearchDto(
                repositories = listOf(
                    RepositoryDto(1, OwnerDto("", "teste"), "nome", 1, 1),
                    RepositoryDto(2, OwnerDto("", "teste2"), "nome2", 2, 2)
                )
            )
            coEvery {
                searchService.getSearchRepositories(
                    query = "language:kotlin",
                    sort = "stars",
                    page = 1
                )
            } returns searchDto
            val params: PagingSource.LoadParams.Refresh<Int> = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )

            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = searchDto.toSearchRepositories().repositories,
                    prevKey = null,
                    nextKey = 2
                ), actual = pagingSource.load(params)
            )
        }
    }

    @Test
    fun `reviews paging source load - failure - http error`() = runTest {
        val error = RuntimeException("404", Throwable())
        coEvery { searchService.getSearchRepositories(any(), any(), any()) }.throws(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, Repository>(error)
        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}
