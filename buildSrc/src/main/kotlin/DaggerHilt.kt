import org.gradle.api.artifacts.dsl.DependencyHandler

object DaggerHilt {

    private const val hiltVersion = "2.44.2"

    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
}

fun DependencyHandler.daggerHilt() {
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)
}