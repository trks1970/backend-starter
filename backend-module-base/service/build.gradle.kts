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
    compileOnly(libs.lombok)
    annotationProcessor(libs.bundles.annotationProcessorBundle)

    testImplementation(libs.bundles.testBundle)

}


