@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.willir.rust.CargoNdkBuildTask
import org.jetbrains.kotlin.konan.properties.hasProperty
import org.jetbrains.kotlin.konan.properties.propertyList

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.cargoNdkAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "rs.scdownload"
    compileSdk = 35

    defaultConfig {
        applicationId = "rs.scdownload"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        ndk {
            abiFilters.addAll(listOf("arm64-v8a", "armeabi-v7a", "x86_64", "x86"))
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
        prefab = true
    }

    splits {
        abi {
            isEnable = true.
            reset()
            include("arm64-v8a", "armeabi-v7a", "x86_64", "x86")
            isUniversalApk = true
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
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.games.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    
	}

	if (System.getenv("GITHUB_ACTIONS") != null) {
	    tasks.withType<CargoNdkBuildTask> {
	        enabled = false
    	}
	}

	cargoNdk {
	    module = "."
	    apiLevel = 26
	    buildType = "release"
	
	    val localProperties = gradleLocalProperties(rootDir, providers)
	
	if (localProperties.hasProperty("ndkTargets")) {
	    targets = ArrayList(localProperties.propertyList("ndkTargets"))
	}
}
