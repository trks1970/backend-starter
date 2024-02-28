val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    `java-library`
}

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.findVersion("jdkVersion").get().toString()))
    }
}

tasks.compileJava {
    // See: https://docs.oracle.com/en/java/javase/12/tools/javac.html
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            //"-Xlint:deprecation,unchecked,divzero,empty,fallthrough,finally,cast,overrides,overloads,rawtypes,static,try,varargs",
            "-Xlint:all,-processing,-serial",
            "-Werror" // Terminates compilation when warnings occur.

        )
    )
    options.encoding = "UTF-8"
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
