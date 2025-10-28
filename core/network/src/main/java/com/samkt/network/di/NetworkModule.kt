package com.samkt.network.di

import com.samkt.network.PortfolioApiService
import com.samkt.network.PortfolioApiServiceImpl
import com.samkt.network.getKtorClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
  single<HttpClient> { getKtorClient() }
  single<PortfolioApiService> { PortfolioApiServiceImpl(client = get()) }
}
