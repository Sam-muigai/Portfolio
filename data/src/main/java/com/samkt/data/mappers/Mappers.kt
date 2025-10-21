package com.samkt.data.mappers

import com.samkt.domain.models.UserInformation
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