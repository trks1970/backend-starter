import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java-conventions")
    id("org.springframework.boot")
}

springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo {
        properties {
            version.set("${project.version}")
        }
    }
}
// val implementation by configurations
dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
}