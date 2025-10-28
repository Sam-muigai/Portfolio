plugins {
    id("com.samkt.library")
    id("com.samkt.spotless")
}

android {
    namespace = "com.samkt.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:network"))
}