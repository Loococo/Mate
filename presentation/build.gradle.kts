plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}
android {
    namespace = "app.loococo.presentation"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        buildToolsVersion = AppConfig.buildToolsVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = AppConfig.jvmTarget
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfig.kotlinCompilerExtensionVersion
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Version.KTX.CORE)

    implementation(Version.COMPOSE.ACTIVITY)
    implementation(platform(Version.COMPOSE.BOM))
    implementation(Version.COMPOSE.UI)
    implementation(Version.COMPOSE.NAVIGATION)

    implementation(Version.AndroidX.MATERIAL3)

    implementation(Version.HILT.HILT_ANDROID)
    implementation(Version.HILT.HILT_VIEWMODEL)

    kapt(Version.HILT.HILT_ANDROID_COMPILER)

    implementation(Version.AndroidX.SPLASH)


    implementation(Version.ORBIT.ORBIT_VIEWMODEL)
    implementation(Version.ORBIT.ORBIT_COMPOSE)
}