package com.samkt.data.repositories

import com.samkt.data.mappers.toData
import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Contact
import com.samkt.domain.repositories.MessageRepository
import com.samkt.network.PortfolioApiService
import com.samkt.network.helpers.ApiResponse

class MessageRepositoryImpl(
  private val portfolioApiService: PortfolioApiService,
) : MessageRepository {
  override suspend fun sendMessage(contact: Contact): Result<String> {
    return when (val response = portfolioApiService.sendMessage(contact.toData())) {
      is ApiResponse.Error -> Result.Error(response.message)
      is ApiResponse.Success -> Result.Success(response.data.message)
    }
  }
}
