plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.android.classloader"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.android.classloader"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation( libs.androidx.core.ktx.v1101)
    implementation( libs.androidx.appcompat.v161)
    implementation( libs.material.v190)

    // Jetpack Compose
    implementation( libs.androidx.ui)
    implementation( libs.androidx.material)
    implementation( libs.androidx.ui.tooling.preview)
    implementation( libs.androidx.activity.compose)
    implementation( libs.androidx.navigation.compose)

    // Room 数据库
    implementation( libs.androidx.room.runtime)
    kapt ("androidx.room:room-compiler:2.5.1")
    implementation( libs.androidx.room.ktx)

    // Lifecycle
    implementation( libs.androidx.lifecycle.runtime.ktx)
    implementation( libs.androidx.lifecycle.viewmodel.compose)

    // Coroutines
    implementation( libs.kotlinx.coroutines.android)

    // 日志库 Timber
    implementation( libs.com.jakewharton.timber.timber)

    // MPAndroidChart 用于性能分析图表展示（可选）
    implementation( libs.mpandroidchart)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}