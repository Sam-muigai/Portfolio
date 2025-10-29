package com.samkt.network

import com.samkt.network.dtos.ContactRequest
import com.samkt.network.dtos.ContactResponse
import com.samkt.network.dtos.ExperienceResponse
import com.samkt.network.dtos.ProjectResponse
import com.samkt.network.dtos.SocialMediaResponse
import com.samkt.network.dtos.UserResponse
import com.samkt.network.helpers.ApiResponse
import com.samkt.network.helpers.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PortfolioApiServiceImpl(
  private val client: HttpClient,
) : PortfolioApiService {
  override suspend fun getUserInformation(userId: Int): ApiResponse<UserResponse> {
    return safeApiCall {
      client.get("$BASE_URL/user?userId=$userId")
    }
  }

  override suspend fun getProjects(userId: Int): ApiResponse<List<ProjectResponse>> {
    return safeApiCall {
      client.get("$BASE_URL/project/all?userId=$userId")
    }
  }

  override suspend fun getExperiences(userId: Int): ApiResponse<List<ExperienceResponse>> {
    return safeApiCall {
      client.get("$BASE_URL/experience/all?userId=$userId")
    }
  }

  override suspend fun getSocialMediaInformation(userId: Int): ApiResponse<SocialMediaResponse> {
    return safeApiCall {
      client.get("$BASE_URL/social-media?userId=$userId")
    }
  }

  override suspend fun sendMessage(contactRequest: ContactRequest): ApiResponse<ContactResponse> {
    return safeApiCall {
      client.post("$BASE_URL/contact") {
        contentType(ContentType.Application.Json)
        setBody(contactRequest)
      }
    }
  }

  companion object {
    const val BASE_URL = "https://backend.sammuigai.xyz"
  }
}
