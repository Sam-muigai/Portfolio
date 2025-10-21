package com.samkt.network

import com.samkt.network.dtos.UserResponse
import com.samkt.network.helpers.ApiResponse
import com.samkt.network.helpers.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class PortfolioApiServiceImpl(
    private val client: HttpClient
) : PortfolioApiService {
    override suspend fun getUserInformation(userId: Int): ApiResponse<UserResponse> {
        return safeApiCall {
            client.get("$BASE_URL/user?userId=$userId")
        }
    }

    companion object {
        const val BASE_URL = "https://backend.sammuigai.xyz"
    }
}