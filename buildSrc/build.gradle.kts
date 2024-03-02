val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

group = "de.lcag.jbox"

plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main/kotlin'
    // that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    mavenCentral()
}

dependencies {

    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/
    // The Spring Boot Gradle Plugin provides Spring Boot support in Gradle.
    // It allows you to package executable jar or war archives, run Spring Boot applications,
    // and use the dependency management provided by spring-boot-dependencies
    add("implementation", libs.findLibrary("springBoot-gradle").get())
    add("implementation", libs.findLibrary("spotless-gradle").get())
    add("implementation", libs.findLibrary("sonar-gradle").get())
}