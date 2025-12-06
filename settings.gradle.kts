// settings.gradle.kts

rootProject.name = "chatflow"

// Incluir el build-logic como composite build
pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }

}

// Módulos compartidos
include(":shared:domain")
include(":shared:events")

// Servicios
include(":services:user-service")
include(":services:chat-service")
include(":services:notification-service")