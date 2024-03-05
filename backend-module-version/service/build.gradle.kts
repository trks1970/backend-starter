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
    implementation(libs.bundles.springServiceBundle)
    implementation(libs.bundles.springSecurityBundle)
    implementation(libs.bundles.springTelemetryBundle)
    implementation(libs.bundles.databaseBundle)
    compileOnly(libs.lombok)

    annotationProcessor(libs.bundles.annotationProcessorBundle)

    testImplementation(libs.bundles.testBundle)
    testImplementation(libs.bundles.integrationTestBundle)

}


