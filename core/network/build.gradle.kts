plugins {
    id("com.samkt.library")
    id("com.samkt.spotless")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.samkt.network"
}

dependencies {
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization)
  //  implementation(libs.kotlinx.serialization.core)
}