package com.samkt.domain.models


data class Project(
    val description: String,
    val id: Int,
    val imageUrl: String,
    val projectUrl: String,
    val title: String
)