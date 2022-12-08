package com.gabrielgrs2.listrepos.domain.model

data class Search(
    val repositories: List<Repository>
)

data class Repository(
    val owner: Owner,
    val name: String,
    val forksCount: Int,
    val starGazersCount: Int
)

data class Owner(
    val avatarUrl: String,
    val login: String
)