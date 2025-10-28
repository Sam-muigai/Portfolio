package com.samkt.data.mappers

import com.samkt.domain.models.Experience
import com.samkt.domain.models.Project
import com.samkt.domain.models.SocialMedia
import com.samkt.domain.models.UserInformation
import com.samkt.network.dtos.ExperienceResponse
import com.samkt.network.dtos.ProjectResponse
import com.samkt.network.dtos.SocialMediaResponse
import com.samkt.network.dtos.UserResponse

fun UserResponse.toDomain(): UserInformation {
  return UserInformation(
    name = name,
    email = email,
    about = about,
    country = country,
    role = role,
    profileImage = profileImage,
  )
}

fun ProjectResponse.toDomain(): Project {
  return Project(
    id = id,
    description = description,
    imageUrl = imageUrl,
    projectUrl = projectUrl,
    title = title,
  )
}

fun ExperienceResponse.toDomain(): Experience {
  return Experience(
    companyName = companyName,
    description = description,
    fromDate = fromDate,
    toDate = toDate,
    title = title,
    location = location,
  )
}

fun SocialMediaResponse.toDomain(): SocialMedia {
  return SocialMedia(
    githubUrl = githubUrl,
    linkedinUrl = linkedinUrl,
    xUrl = xUrl,
    youtubeUrl = youtubeUrl,
    portfolioUrl = portfolioUrl,
  )
}
