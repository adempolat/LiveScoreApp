plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.adempolat.livescoreapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.adempolat.livescoreapp"
        minSdk = 24
        targetSdk = 34
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
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //Lifecycle Extensions
    implementation(libs.lifecycle.extensions)

    //Lifecycle Runtime
    implementation(libs.lifecycle.runtime.ktx)

    //Coroutine Android
    implementation(libs.kotlinx.coroutines.android)

    //Lottie
    implementation(libs.lottie)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    //Hilt
    implementation(libs.hilt)
    kapt(libs.hiltAndroidCompilerKapt)

    //Okhttp
    implementation(libs.okhttpLoggingInterceptor)

    //Chucker
    implementation(libs.chucker)

    //Fragment Ktx
    implementation(libs.fragmentKtx)

    //Navigation Fragment
    implementation(libs.navigationFragment)

    //Swipe Refresh Layout
    implementation(libs.swipeRefreshLayout)

    //Coil
    implementation(libs.coil)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}