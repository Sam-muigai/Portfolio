package com.samkt.portfolio

import android.app.Application
import com.samkt.data.di.dataModule
import com.samkt.home.di.homeScreenModule
import com.samkt.network.di.networkModule
import com.samkt.projects.di.projectScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            homeScreenModule,
            projectScreenModule,
            networkModule,
            dataModule
        )

        startKoin {
            androidLogger()
            androidContext(this@PortfolioApplication)
            modules(modules)
        }
    }
}