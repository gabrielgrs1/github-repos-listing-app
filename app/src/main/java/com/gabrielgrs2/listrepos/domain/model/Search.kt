package com.gabrielgrs2.listrepos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search(
    val repositories: List<Repository>
) : Parcelable

@Parcelize
data class Repository(
    val id: Int,
    val owner: Owner,
    val name: String,
    val forksCount: Int,
    val starGazersCount: Int
) : Parcelable

@Parcelize
data class Owner(
    val avatarUrl: String,
    val login: String
) : Parcelable