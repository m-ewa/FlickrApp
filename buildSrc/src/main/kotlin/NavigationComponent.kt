import org.gradle.api.artifacts.dsl.DependencyHandler

object NavigationComponent {

    private const val navigationVersion = "2.5.3"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
}

fun DependencyHandler.navigation() {
    implementation(NavigationComponent.navigationFragment)
    implementation(NavigationComponent.navigationUi)
}