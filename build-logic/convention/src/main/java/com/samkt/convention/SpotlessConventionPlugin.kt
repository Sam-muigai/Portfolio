package com.samkt.convention

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            extensions.configure<SpotlessExtension> {
                val buildDirectory = layout.buildDirectory.asFileTree
                kotlin {
                    target("**/*.kt")
                    targetExclude(buildDirectory)
                    ktlint().editorConfigOverride(
                        mapOf(
                            "indent_size" to "2",
                            "continuation_indent_size" to "2",
                            "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
                        )
                    )
                    trimTrailingWhitespace()
                    endWithNewline()
                }
                format("kts") {
                    target("**/*.kts")
                    targetExclude(buildDirectory)
                }
                format("xml") {
                    target("**/*.xml")
                    targetExclude(buildDirectory)
                }
            }
            afterEvaluate {
                tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
                    finalizedBy("spotlessApply")
                }
            }
        }
    }
}