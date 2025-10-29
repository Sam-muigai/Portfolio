package com.samkt.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactRequest(
  @SerialName("email")
  val email: String,
  @SerialName("message")
  val message: String,
  @SerialName("name")
  val name: String,
)
