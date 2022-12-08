package com.gabrielgrs2.listrepos.data.dto

import com.squareup.moshi.Json

data class SearchDto(
    @field:Json(name = "total_count") val totalCount: Int,
    @field:Json(name = "items") val repositories: List<RepositoryDto>
)

data class RepositoryDto(
    @field:Json(name = "owner") val owner: OwnerDto,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "forks_count") val forksCount: Int,
    @field:Json(name = "stargazers_count") val starGazersCount: Int
)

data class OwnerDto(
    @field:Json(name = "avatar_url") val avatarUrl: String,
    @field:Json(name = "login") val login: String
)