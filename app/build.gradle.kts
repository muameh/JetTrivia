plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mehmetbaloglu.jettrivia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mehmetbaloglu.jettrivia"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    //hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    //viewmodel
    val lifecycle_version = "2.8.6"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    // Retrofit for API requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


//// Coroutines
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
//implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.5.2"
//
//// Coroutine Lifecycle Scopes
//implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
//implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
//
////Dagger - Hilt
//implementation 'com.google.dagger:hilt-android:2.39'
////May need okkhttp also
//
//// Dagger - Hilt
//kapt "com.google.dagger:hilt-android-compiler:$hilt_version"
//implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
//kapt "androidx.hilt:hilt-compiler:1.0.0"
//implementation "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
//
////Retrofit
//implementation 'com.squareup.retrofit2:retrofit:2.9.0'
////GSON converter
//implementation 'com.squareup.retrofit2:converter-gson:2.9.0'