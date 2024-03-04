import org.gradle.api.artifacts.dsl.DependencyHandler

object Ui {

    private const val coilVersion = "2.2.2"
    const val coil = "io.coil-kt:coil:$coilVersion"

    private const val materialVersion = "1.7.0"
    const val material = "com.google.android.material:material:$materialVersion"

    private const val constraintLayoutVersion = "2.1.4"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    private const val recyclerviewVersion = "1.3.0-rc01"
    const val recyclerview = "androidx.recyclerview:recyclerview:$recyclerviewVersion"
}

fun DependencyHandler.ui() {
    implementation(Ui.coil)
    implementation(Ui.material)
    implementation(Ui.constraintLayout)
    implementation(Ui.recyclerview)
}