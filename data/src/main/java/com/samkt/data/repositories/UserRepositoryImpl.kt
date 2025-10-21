package com.samkt.data.repositories

import com.samkt.data.mappers.toDomain
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.UserInformation
import com.samkt.domain.repositories.UserRepository
import com.samkt.network.PortfolioApiService
import com.samkt.network.helpers.ApiResponse

class UserRepositoryImpl(
    private val portfolioApiService: PortfolioApiService
) : UserRepository {
    override suspend fun getUserInformation(userId: Int): Result<UserInformation> {
        return when (val response = portfolioApiService.getUserInformation(userId)) {
            is ApiResponse.Error -> Result.Error(response.message)
            is ApiResponse.Success -> Result.Success(response.data.toDomain())
        }
    }
}