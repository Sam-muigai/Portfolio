package com.samkt.portfolio

import android.app.Application
import com.samkt.home.di.homeScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PortfolioApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            homeScreenModule
        )

        startKoin {
            androidLogger()
            androidContext(this@PortfolioApplication)
            modules(modules)
        }
    }
}