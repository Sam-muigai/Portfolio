package com.samkt.convention

import com.android.build.api.dsl.ApplicationExtension
import com.samkt.convention.configuration.configureKotlinAndroid
import com.samkt.convention.configuration.implementation
import com.samkt.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk =
                    libs.findVersion("targetSdk").get().toString().toInt()
            }

            dependencies {
                implementation(libs.findLibrary("koin-android").get())
            }
        }
    }
}