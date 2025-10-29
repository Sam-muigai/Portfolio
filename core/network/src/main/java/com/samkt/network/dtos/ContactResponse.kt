package com.samkt.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
  @SerialName("message")
  val message: String,
  @SerialName("status")
  val status: Int,
  @SerialName("timestamp")
  val timestamp: Long,
)
