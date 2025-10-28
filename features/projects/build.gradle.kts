plugins {
    id("com.samkt.feature")
    id("com.samkt.spotless")
}

android {
    namespace = "com.samkt.projects"
}

dependencies {
    implementation(libs.coil.compose)
}