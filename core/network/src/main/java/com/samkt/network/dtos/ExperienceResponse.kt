package com.samkt.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceResponse(
  @SerialName("company_name")
  val companyName: String,
  @SerialName("description")
  val description: String,
  @SerialName("from_date")
  val fromDate: String,
  @SerialName("location")
  val location: String,
  @SerialName("title")
  val title: String,
  @SerialName("to_date")
  val toDate: String,
)
