import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("spring-conventions")
    alias(libs.plugins.openApiGenerator)
}


var apiBasePackage = "de.lcag.jbox.backend.api"
val buildDir = "${layout.buildDirectory.get()}"

var generatorConfig = mapOf(
    Pair("enumPropertyNaming", "original"),
    Pair("modelMutable", "true"),
    Pair("basePackage", apiBasePackage),
    Pair("apiPackage", apiBasePackage),
    Pair("modelPackage", "$apiBasePackage.resource"),
    Pair("gradleBuildFile", "false"),
    Pair("exceptionHandler", "false"),
    Pair("interfaceOnly", "true"),
    Pair("useTags", "true"),
    Pair("useBeanValidation", "true"),
    Pair("generateSupportingFiles", "false"),
    Pair("documentationProvider", "none"),
    Pair("annotationLibrary", "swagger2"),
    Pair("skipDefaultInterface", "true"),
    Pair("useSpringBoot3", "true"),
    )

tasks.register("generateApi", GenerateTask::class.java) {
    description = "Generate Api code"
    group = JavaBasePlugin.BUILD_TASK_NAME

    generatorName.set("spring")
    library.set("spring-boot")
    inputSpec.set("$projectDir/src/main/resources/api.yaml")
    //templateDir.set("$projectDir/src/main/resources/templates")
    outputDir.set("$buildDir/generated")
    modelNameSuffix.set("Resource")
    globalProperties.put("skipFormModel", "false")
    openapiNormalizer.put("REMOVE_ANYOF_ONEOF_AND_KEEP_PROPERTIES_ONLY", "true")
    configOptions.putAll(generatorConfig)
}

tasks.withType<JavaCompile> {
    dependsOn(
        tasks.findByName("generateApi"),
    )
    sourceSets.main {
        java.srcDirs(
            "$projectDir/src/main/java",
            "${layout.buildDirectory.get()}/generated/src/main/java",
        )
    }
}

dependencies {
    implementation(libs.bundles.springApiBundle)

}
