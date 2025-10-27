package com.samkt.data.di

import com.samkt.data.repositories.ExperienceRepositoryImpl
import com.samkt.data.repositories.ProjectRepositoryImpl
import com.samkt.data.repositories.UserRepositoryImpl
import com.samkt.domain.repositories.ExperienceRepository
import com.samkt.domain.repositories.ProjectRepository
import com.samkt.domain.repositories.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ProjectRepository> { ProjectRepositoryImpl(get()) }
    single<ExperienceRepository> { ExperienceRepositoryImpl(get()) }
}