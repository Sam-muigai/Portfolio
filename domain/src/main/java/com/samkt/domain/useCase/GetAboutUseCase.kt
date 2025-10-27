package com.samkt.domain.useCase

import com.samkt.domain.helpers.Result
import com.samkt.domain.models.AboutMe
import com.samkt.domain.repositories.ExperienceRepository
import com.samkt.domain.repositories.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetAboutUseCase(
    private val userRepository: UserRepository,
    private val experienceRepository: ExperienceRepository
) {
    suspend operator fun invoke(userId: Int): Result<AboutMe> = coroutineScope {

        val userTaskDeferred = async { userRepository.getUserInformation(userId) }
        val experienceTaskDeferred = async { experienceRepository.getExperiences(userId) }

        val user = (userTaskDeferred.await() as? Result.Success)?.data
        val experiences = (experienceTaskDeferred.await() as? Result.Success)?.data

        if (user == null && experiences == null) {
            return@coroutineScope Result.Error("Failed to fetch user information and experiences")
        }

        return@coroutineScope Result.Success(
            AboutMe(
                user = user,
                experiences = experiences
            )
        )
    }
}

