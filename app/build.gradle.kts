plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}
apply("../shared_dependencies.gradle")

android {
    namespace = "com.andricohalim.movieapps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.andricohalim.movieapps"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYmRiYjhlM2E0NzY0NmYxZTY2MjI4MWZiYjZkMjE1ZSIsInN1YiI6IjY1Yjc2M2QyMTA4OWJhMDE2NGY4OGEzOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9Bq_0E0WDZ137CQieR86xhQE3flVjbGBkwX_dzpHN60\"")
        buildConfigField("String", "IMAGE_URL", "\"https://image.tmdb.org/t/p/original/\"")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
}