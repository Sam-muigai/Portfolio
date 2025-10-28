plugins {
    id("com.samkt.feature")
    id("com.samkt.spotless")
}

android {
    namespace = "com.samkt.about"
}

dependencies {
    implementation(libs.coil.compose)
}