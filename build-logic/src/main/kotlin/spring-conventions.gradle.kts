import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
// if you swap these lines, you can do module-wise versioning
// by defining version in the gradle.properties file of each module
// project.version = "${project.properties["version"]}-${System.getenv()["COMMIT_SLUG"] ?: "local"}"
project.version = "${libs.findVersion("backendVersion").get()}-${System.getenv()["COMMIT_SLUG"] ?: "local"}"

plugins {
    id("java-conventions")
    id("org.springframework.boot")
}

tasks.processResources {
    filesMatching("**/application.yaml") {
        filter {
            it.replace("@version@", project.version as String)
        }
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