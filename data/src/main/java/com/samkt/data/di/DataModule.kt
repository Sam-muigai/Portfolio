package com.samkt.data.di

import com.samkt.data.repositories.UserRepositoryImpl
import com.samkt.domain.repositories.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}