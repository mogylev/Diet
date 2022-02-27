plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ), "proguard-rules.pro"
            )
        }


    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)


    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation(Navigation.navigationFragment)
    implementation(Navigation.navigationUI)

    //dagger
    implementation(Dagger.dagger)
    kapt(Dagger.daggerCompiler)
//
    implementation(Room.roomRuntime)
    annotationProcessor(Room.roomCompiler)
//     To use Kotlin annotation processing tool (kapt)
    kapt(Room.roomCompiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(Room.roomKtx)

//    // optional - Paging 3 Integration
//    implementation("androidx.room:room-paging:2.4.0")

    implementation ("com.google.code.gson:gson:2.8.9")
//
    implementation("androidx.datastore:datastore-preferences:1.0.0")
//
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation "androidx.compose.ui:ui-test-junit4:$libs.compose"
}