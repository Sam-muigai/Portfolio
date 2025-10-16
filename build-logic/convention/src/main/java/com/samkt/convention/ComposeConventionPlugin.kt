package com.samkt.convention

import com.android.build.gradle.LibraryExtension
import com.samkt.convention.configuration.configureAndroidCompose
import com.samkt.convention.configuration.implementation
import com.samkt.convention.configuration.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.samkt.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                implementation(platform(bom))
                implementation(libs.findBundle("compose").get())
            }
        }
    }
}