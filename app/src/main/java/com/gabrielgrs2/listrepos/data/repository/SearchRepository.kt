package com.gabrielgrs2.listrepos.data.repository

import com.gabrielgrs2.listrepos.core.utils.Result
import com.gabrielgrs2.listrepos.data.api.IApiCore
import com.gabrielgrs2.listrepos.data.mapper.toSearchRepositories
import com.gabrielgrs2.listrepos.domain.model.Search
import com.gabrielgrs2.listrepos.domain.repository.ISearchRepository
import org.koin.core.component.KoinComponent
import retrofit2.HttpException
import java.io.IOException

class SearchRepository(private val apiCore: IApiCore) : ISearchRepository, KoinComponent {

    override suspend fun getSearchRepositories(page: String): Result<Search> {
        return try {
            val result = apiCore.getSearchRepositories(
                query = "language:kotlin",
                sort = "stars",
                page = "1"
            )
            Result.Success(result.toSearchRepositories())
        } catch (e: IOException) {
            e.printStackTrace()
            Result.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}
