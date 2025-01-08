plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.compose)
    alias(libs.plugins.convention.core.lifecycle)
}

android {
    namespace = "com.lmorda.explore"
}

dependencies {
    implementation(project(":design"))
    implementation(project(":domain"))
    implementation(libs.compose.glide)
    implementation(libs.lottie)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}
