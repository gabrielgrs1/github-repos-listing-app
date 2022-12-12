package com.gabrielgrs2.listrepos.data.mapper

import com.gabrielgrs2.listrepos.data.dto.OwnerDto
import com.gabrielgrs2.listrepos.data.dto.SearchDto
import com.gabrielgrs2.listrepos.domain.model.Owner
import com.gabrielgrs2.listrepos.domain.model.Repository
import com.gabrielgrs2.listrepos.domain.model.Search

fun SearchDto.toSearchRepositories(): Search {
    return Search(
        repositories = repositories.map { repository ->
            Repository(
                id = repository.id,
                owner = repository.owner.toOwner(),
                name = repository.name,
                forksCount = repository.forksCount,
                starGazersCount = repository.starGazersCount
            )
        }
    )
}

fun OwnerDto.toOwner(): Owner {
    return Owner(
        avatarUrl = avatarUrl,
        login = login
    )
}