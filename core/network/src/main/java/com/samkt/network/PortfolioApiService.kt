package com.samkt.network

import com.samkt.network.dtos.UserResponse
import com.samkt.network.helpers.ApiResponse

interface PortfolioApiService {

    suspend fun getUserInformation(userId: Int): ApiResponse<UserResponse>
}