import org.sonarqube.gradle.SonarExtension

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    `java-library`
    checkstyle
    jacoco
    id("com.diffplug.spotless")
    if (System.getenv("TF_BUILD") != "True") {
        id("org.sonarqube")
    }

}

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.findVersion("jdkVersion").get().toString()))
    }
}

spotless {
    java {
        target("src/*/java/**/*.java")
        googleJavaFormat()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("$projectDir/*.gradle.kts")
        ktfmt("${libs.findVersion("ktfmt").get()}")
            .googleStyle()
            .configure { options ->
                run {
                    options.setRemoveUnusedImport(true)
                }
            }
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.checkstyleMain {
    dependsOn(tasks.findByName("spotlessApply"))
}

checkstyle {
    isIgnoreFailures = false
    maxWarnings = 0
    maxErrors = 0
    toolVersion = libs.findVersion("checkstyle").get().toString()
}

tasks.compileJava {
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all,-processing",
            "-Werror" // Terminates compilation when warnings occur.

        )
    )
    options.encoding = "UTF-8"
}


tasks.withType<Test> {
    testLogging.showStandardStreams = true
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    jvmArgs(
        "-XX:+EnableDynamicAgentLoading",
    )
}

// JaCoCo--
jacoco {
    toolVersion = libs.findVersion("jacoco").get().toString()
}

tasks.withType<JacocoReport>() {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map { file ->
            fileTree(file).apply {
                exclude("**/infrastructure/entity/**")
                exclude("**/*Application.java")
            }
        }))
    }
}

tasks.withType<JacocoCoverageVerification>() {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map { file ->
            fileTree(file).apply {
                exclude("**/infrastructure/entity/**")
                exclude("**/*Application.java")
            }
        }))
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal.valueOf(0.80)
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

// -- JaCoCo

// Sonar --
// see: https://community.sonarsource.com/t/unresolved-s-types-have-been-detected-during-analysis/43321
if (System.getenv("TF_BUILD") != "True") {
    project.extensions.getByType(SonarExtension::class).properties {
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.login", "admin")
        property("sonar.coverage.exclusions", "**/*Entity.kt")
        // you have to set this manually in your local sonar
        property("sonar.password", "changeme")
    }
    // tasks.getByName("sonar").dependsOn(tasks.findByName("test"))
}
// --Sonar


tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

dependencies {
    testImplementation(libs.findLibrary("junit").get())
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
