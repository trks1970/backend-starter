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

    api(project(":backend-starter-api"))
    implementation(libs.bundles.springApiBundle)

    // see: https://hibernate.atlassian.net/browse/HHH-17612
    implementation(libs.bundles.springDataEnvers) {
        exclude("org.hibernate.orm","hibernate-envers")
    }
    implementation(libs.hibernateEnversDowngrade)

    implementation(libs.bundles.springServiceBundle)
    implementation(libs.bundles.springSecurityBundle)
    implementation(libs.bundles.springTelemetryBundle)
    implementation(libs.bundles.databaseBundle)

    implementation(libs.mapstruct)
    compileOnly(libs.lombok)

    annotationProcessor(libs.bundles.annotationProcessorBundle)

    testImplementation(libs.bundles.testBundle)
    testImplementation(libs.bundles.integrationTestBundle)

}


