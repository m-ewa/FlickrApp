import org.gradle.api.artifacts.dsl.DependencyHandler

object Room {

    private const val roomVersion = "2.6.1"

    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
}

fun DependencyHandler.room() {
    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)
}