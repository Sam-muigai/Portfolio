package com.samkt.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectResponse(
  @SerialName("description")
  val description: String,
  @SerialName("id")
  val id: Int,
  @SerialName("image_url")
  val imageUrl: String,
  @SerialName("project_url")
  val projectUrl: String,
  @SerialName("title")
  val title: String,
)
