val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    `java-library`
    id("com.diffplug.spotless")
}

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.findVersion("jdkVersion").get().toString()))
    }
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    java {
        target("src/*/java/**/*.kt")
        googleJavaFormat()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("$projectDir/*.gradle.kts") // default target for kotlinGradle
        ktfmt("${libs.findVersion("ktfmt").get()}")
            .googleStyle()
            .configure { options -> run {
                options.setRemoveUnusedImport(true)
            }
            }
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks.compileJava {
    // See: https://docs.oracle.com/en/java/javase/12/tools/javac.html
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all,-processing,-serial",
            "-Werror" // Terminates compilation when warnings occur.

        )
    )
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    testLogging.showStandardStreams = true
    useJUnitPlatform()
    jvmArgs(
        "-XX:+EnableDynamicAgentLoading",
    )
}

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
