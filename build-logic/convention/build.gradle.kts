plugins {
    `kotlin-dsl`
}

group = "com.lmorda.homework"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("conventionAndroidApplication") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("conventionAndroidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("conventionCoreLifecycle") {
            id = "convention.core.lifecycle"
            implementationClass = "AndroidCoreLifecycleConventionPlugin"
        }
        register("conventionCompose") {
            id = "convention.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}
