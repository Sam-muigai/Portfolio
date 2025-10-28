package com.samkt.network.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialMediaResponse(
  @SerialName("github_url")
  val githubUrl: String,
  @SerialName("linkedin_url")
  val linkedinUrl: String,
  @SerialName("portfolio_url")
  val portfolioUrl: String,
  @SerialName("x_url")
  val xUrl: String,
  @SerialName("youtube_url")
  val youtubeUrl: String,
)
