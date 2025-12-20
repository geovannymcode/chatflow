plugins {
    id("chatflow.spring-service")
    id("org.springframework.boot")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

// Configuración del JAR de Spring Boot
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveClassifier.set("")

    // Configuración de layered jars para Docker
    layered {
        enabled.set(true)
        includeLayerTools.set(true)
    }
}

// El jar normal no se genera (usamos bootJar)
tasks.named<Jar>("jar") {
    enabled = false
}

// Configuración para tests de integración
tasks.named<Test>("test") {
    // Más memoria para tests con containers
    maxHeapSize = "1024m"

    // Paralelismo de tests
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).coerceAtLeast(1)
}

// Task para ejecutar la aplicación en desarrollo
tasks.register("dev") {
    group = "application"
    description = "Run the application in development mode"
    dependsOn("bootRun")
}