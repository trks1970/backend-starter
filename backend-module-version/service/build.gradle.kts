plugins {
    id("spring-conventions")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll( listOf(
        "-Amapstruct.defaultComponentModel=spring",
        "-Amapstruct.defaultInjectionStrategy=constructor",
        "-Amapstruct.unmappedTargetPolicy=ERROR",
        "-Amapstruct.unmappedSourcePolicy=ERROR")
    )
    sourceSets.main {
        java.srcDirs(
            "$projectDir/src/main/java",
            "${layout.buildDirectory.get()}/generated/sources/annotationProcessor/java/main",
        )
    }
}

dependencies {

    api(project(":api"))
    implementation(libs.bundles.springApiBundle)

    implementation(libs.mapstruct)
    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)
    annotationProcessor(libs.mapstructLombokBinding)
    annotationProcessor(libs.mapstructProcessor)

    testImplementation(libs.bundles.testBundle)
    testImplementation(libs.bundles.integrationTestBundle)
    testImplementation(libs.springBootDataJpa)
    testCompileOnly(libs.lombok)

}


