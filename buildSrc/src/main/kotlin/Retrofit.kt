import org.gradle.api.artifacts.dsl.DependencyHandler

object Retrofit {

    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    private const val okHttpVersion = "5.0.0-alpha.12"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"

    private const val moshiVersion = "1.15.1"
    const val moshi = "com.squareup.moshi:moshi-kotlin:$moshiVersion"
}

fun DependencyHandler.retrofit() {
    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.moshi)
}