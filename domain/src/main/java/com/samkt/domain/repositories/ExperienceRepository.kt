package com.samkt.domain.repositories

import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Experience

interface ExperienceRepository {
  suspend fun getExperiences(userId: Int): Result<List<Experience>>
}
