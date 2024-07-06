// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}



buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
//        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
        classpath ("androidx.room:room-compiler:2.5.1")

//        configurations.all {
//            resolutionStrategy {
//                force ("org.jetbrains:annotations:24.0.1")  // Ganti dengan versi terbaru jika ada
//            }
//        }
    }
}