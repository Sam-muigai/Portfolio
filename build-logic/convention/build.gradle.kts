import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.samkt.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.samkt.application"
            implementationClass = "com.samkt.convention.ApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "com.samkt.library"
            implementationClass = "com.samkt.convention.LibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "com.samkt.compose"
            implementationClass = "com.samkt.convention.ComposeConventionPlugin"
        }

        register("androidFeature") {
            id = "com.samkt.feature"
            implementationClass = "com.samkt.convention.FeatureConventionPlugin"
        }
    }
}
