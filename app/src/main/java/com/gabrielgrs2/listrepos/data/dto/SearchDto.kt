package com.gabrielgrs2.listrepos.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchDto(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val repositories: List<RepositoryDto>
) : Parcelable

@Parcelize
data class RepositoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("owner") val owner: OwnerDto,
    @SerializedName("name") val name: String,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("stargazers_count") val starGazersCount: Int
) : Parcelable

@Parcelize
data class OwnerDto(
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("login") val login: String
) : Parcelable