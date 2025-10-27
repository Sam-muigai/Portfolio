package com.samkt.domain.di

import com.samkt.domain.useCase.GetAboutUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::GetAboutUseCase)
}