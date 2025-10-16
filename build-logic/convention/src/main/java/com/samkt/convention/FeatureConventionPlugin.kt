package com.samkt.convention

import com.android.build.gradle.LibraryExtension
import com.samkt.convention.configuration.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.samkt.library")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies {

            }
        }
    }
}