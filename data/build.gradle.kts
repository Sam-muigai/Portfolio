plugins {
    id("com.samkt.library")
}

android {
    namespace = "com.samkt.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:network"))
}