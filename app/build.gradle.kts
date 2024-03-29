plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-platform-android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "com.mewa.flickrapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mewa.flickrapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    moduleDomain()
    moduleData()

    androidX()
    ui()
    daggerHilt()
    navigation()

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5",)
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}