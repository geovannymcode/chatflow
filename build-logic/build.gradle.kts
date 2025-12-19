// build-logic/build.gradle.kts

plugins {
    `kotlin-dsl`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    // Plugins que usaremos en nuestros convention plugins
    implementation(libs.plugins.kotlin.jvm.get().toString())
    implementation(libs.plugins.kotlin.spring.get().toString())
    implementation(libs.plugins.kotlin.jpa.get().toString())
    implementation(libs.plugins.spring.boot.get().toString())
    implementation(libs.plugins.spring.dependency.management.get().toString())

    // Para que los plugins tengan acceso al version catalog
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

// Helper: convertir plugin notation a dependency notation
fun Provider<PluginDependency>.get(): String = get().run {
    "$pluginId:$pluginId.gradle.plugin:${version.requiredVersion}"
}