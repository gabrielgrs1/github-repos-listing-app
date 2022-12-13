package com.gabrielgrs2.listrepos.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.gabrielgrs2.listrepos.base.BaseUTTest
import com.gabrielgrs2.listrepos.domain.model.Owner
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.utils.FakeSearchPagingSource
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
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
    val pagingSource = FakeSearchPagingSource()

    @Before
    fun start() {
        super.setUp()
        MockKAnnotations.init(this)
    }

    @Test
    fun `verify paging data is success when paging correctly loaded`() {
        runTest {
            val params: PagingSource.LoadParams.Refresh<Int> = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )

            val repositories = listOf(
                Repository(1, Owner("", "teste"), "nome", 1, 1),
                Repository(2, Owner("", "teste2"), "nome2", 2, 2)
            )

            assertEquals(
                expected = PagingSource.LoadResult.Page(
                    data = repositories,
                    prevKey = null,
                    nextKey = repositories[1].id
                ), actual = pagingSource.load(params)
            )

        }
    }

}