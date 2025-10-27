package com.samkt.domain.models


data class UserInformation(
    val about: String,
    val country: String,
    val email: String,
    val name: String,
    val role: String,
    val profileImage: String
)