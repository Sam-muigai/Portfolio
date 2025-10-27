package com.samkt.network.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    @SerialName("about")
    val about: String,
    @SerialName("country")
    val country: String,
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("role")
    val role: String,
    @SerialName("social_media")
    val socialMediaResponse: SocialMediaResponse,
    @SerialName("profile_image")
    val profileImage: String
)