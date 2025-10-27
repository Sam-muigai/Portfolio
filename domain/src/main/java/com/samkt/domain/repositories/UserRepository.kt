package com.samkt.domain.repositories

import com.samkt.domain.helpers.Result
import com.samkt.domain.models.SocialMedia
import com.samkt.domain.models.UserInformation

interface UserRepository {
    suspend fun getUserInformation(userId: Int): Result<UserInformation>
    suspend fun getSocialMediaInformation(userId: Int): Result<SocialMedia>
}