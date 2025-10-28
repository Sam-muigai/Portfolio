package com.samkt.data.repositories

import com.samkt.data.mappers.toDomain
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Experience
import com.samkt.domain.repositories.ExperienceRepository
import com.samkt.network.PortfolioApiService
import com.samkt.network.helpers.ApiResponse

class ExperienceRepositoryImpl(
  private val portfolioApiService: PortfolioApiService,
) : ExperienceRepository {
  override suspend fun getExperiences(userId: Int): Result<List<Experience>> {
    return when (val response = portfolioApiService.getExperiences(userId)) {
      is ApiResponse.Error -> Result.Error(response.message)
      is ApiResponse.Success -> Result.Success(response.data.map { it.toDomain() })
    }
  }
}
