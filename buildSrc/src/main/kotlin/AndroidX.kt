import org.gradle.api.artifacts.dsl.DependencyHandler

object AndroidX {

    private const val coreKtxVersion = "1.9.0"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

    private const val appCompatVersion = "1.6.0"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val activityVersion = "1.6.1"
    const val activity = "androidx.activity:activity-ktx:$activityVersion"

    private const val fragmentVersion = "1.5.5"
    const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"

    private const val lifecycleVersion = "2.5.1"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
}

fun DependencyHandler.androidX() {
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.activity)
    implementation(AndroidX.fragment)
    implementation(AndroidX.lifecycleRuntime)
}