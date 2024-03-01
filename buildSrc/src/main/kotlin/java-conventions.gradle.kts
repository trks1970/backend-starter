val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    `java-library`
    checkstyle
    id("com.diffplug.spotless")
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

tasks.checkstyleMain {
    dependsOn(tasks.findByName("spotlessApply"))
}

checkstyle {
    isIgnoreFailures = false
    maxWarnings = 0
    maxErrors = 0
    // configFile = file("${rootProject.rootDir}/config/checkstyle/google_checks.xml")
    toolVersion = libs.findVersion("checkstyle").get().toString()
}

tasks.compileJava {
    //dependsOn(tasks.findByName("checkstyleMain"))
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
