import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

project.version = "${project.properties["version"]}-${System.getenv()["COMMIT_SLUG"] ?: "local"}"

plugins {
    id("java-conventions")
    id("org.springframework.boot")
}

tasks.processResources {
    filesMatching("application.yml") {
        expand(project.properties)
    }
}

// --SpringBoot
springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo {
        properties {
            version.set("${project.version}")
        }
    }
}
tasks.withType<BootJar> {
    onlyIf {
        project.name.endsWith("-service")
    }
    archiveClassifier.set("boot")
}
// --SpringBoot



dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
}