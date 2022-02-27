object Build {
    private const val androidBuildToolsVersion = "7.0.4"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"

    const val daggerAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${Dagger.version}"

    const val navigationSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Navigation.navigationVersion}"


}