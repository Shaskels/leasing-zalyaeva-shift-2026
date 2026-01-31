plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.leasing_zalyaeva_shift_2026"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.leasing_zalyaeva_shift_2026"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

kotlin{
    jvmToolchain(17)
}

dependencies {
    implementation(project(":component:uicomponent"))
    implementation(project(":shared:rent"))
    implementation(project(":shared:filter"))
    implementation(project(":feature:carList"))
    implementation(project(":feature:carDetails"))
    implementation(project(":feature:rentSuccess"))
    implementation(project(":feature:rentCar"))
    implementation(project(":feature:filter"))

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.material3.adaptive.navigation3)

    implementation(libs.hilt.android.runtime)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}