plugins {
    id("spring-conventions")
}


dependencies {

    api(project(":backend-starter-api"))
    implementation(libs.bundles.springApiBundle)
    implementation(libs.bundles.springServiceBundle)
    implementation(libs.bundles.springSecurityBundle)
    implementation(libs.bundles.springTelemetryBundle)

    compileOnly(libs.bundles.annotationProcessorBundle)
    annotationProcessor(libs.bundles.annotationProcessorBundle)

    testImplementation(libs.bundles.testBundle)
    testImplementation(libs.bundles.integrationTestBundle)

}


