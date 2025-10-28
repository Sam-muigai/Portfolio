package com.samkt.network

import com.samkt.network.dtos.ExperienceResponse
import com.samkt.network.dtos.ProjectResponse
import com.samkt.network.dtos.SocialMediaResponse
import com.samkt.network.dtos.UserResponse
import com.samkt.network.helpers.ApiResponse

interface PortfolioApiService {

  suspend fun getUserInformation(userId: Int): ApiResponse<UserResponse>

  suspend fun getProjects(userId: Int): ApiResponse<List<ProjectResponse>>

  suspend fun getExperiences(userId: Int): ApiResponse<List<ExperienceResponse>>

  suspend fun getSocialMediaInformation(userId: Int): ApiResponse<SocialMediaResponse>
}
