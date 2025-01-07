plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.lmorda.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
}
