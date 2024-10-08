// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    //hilt
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}

buildscript {
    dependencies {
        // Hilt Plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
    }
}