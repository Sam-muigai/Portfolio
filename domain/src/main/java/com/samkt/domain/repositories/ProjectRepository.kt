package com.samkt.domain.repositories

import com.samkt.domain.helpers.Result
import com.samkt.domain.models.Project

interface ProjectRepository {
  suspend fun getAllProjects(userId: Int): Result<List<Project>>
}
