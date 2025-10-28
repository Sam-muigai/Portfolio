plugins {
    id("com.samkt.feature")
    id("com.samkt.spotless")
}

android {
    namespace = "com.samkt.home"
}

dependencies {
    implementation(libs.coil.compose)
}