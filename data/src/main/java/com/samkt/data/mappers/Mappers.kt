package com.samkt.data.mappers

import com.samkt.domain.models.Project
import com.samkt.domain.models.UserInformation
import com.samkt.network.dtos.ProjectResponse
import com.samkt.network.dtos.UserResponse

fun UserResponse.toDomain(): UserInformation {
    return UserInformation(
        name = name,
        email = email,
        about = about,
        country = country,
        role = role
    )
}

fun ProjectResponse.toDomain(): Project {
    return Project(
        id = id,
        description = description,
        imageUrl = imageUrl,
        projectUrl = projectUrl,
        title = title
    )
}