
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
    implementation("org.springframework.boot:spring-boot-gradle-plugin:${rootProject.libs.versions.spring.get()}")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:${rootProject.libs.versions.spotless.get()}")
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:${rootProject.libs.versions.sonar.get()}")
}