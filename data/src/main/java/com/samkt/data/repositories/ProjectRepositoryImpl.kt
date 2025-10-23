package com.samkt.data.repositories

import com.samkt.data.mappers.toDomain
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Project
import com.samkt.domain.repositories.ProjectRepository
import com.samkt.network.PortfolioApiService
import com.samkt.network.helpers.ApiResponse

class ProjectRepositoryImpl(private val portfolioApiService: PortfolioApiService) :
    ProjectRepository {
    override suspend fun getAllProjects(userId: Int): Result<List<Project>> {
        return when (val response = portfolioApiService.getProjects(userId)) {
            is ApiResponse.Error -> Result.Error(response.message)
            is ApiResponse.Success -> Result.Success(response.data.map { it.toDomain() })
        }
    }
}