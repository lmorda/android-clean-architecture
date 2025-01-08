plugins {
    alias(libs.plugins.convention.application)
    alias(libs.plugins.convention.compose)
}

android {
    namespace = "com.lmorda.homework"
    defaultConfig {
        applicationId = "com.lmorda.homework"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":design"))
    implementation(project(":domain"))
    implementation(project(":explore"))
    implementation(project(":utils"))
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
}
