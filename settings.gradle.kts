
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "backend-starter"
includeBuild("build-logic")
includeBuild("backend-support-test")
includeBuild("backend-module-base")
includeBuild("backend-module-version")
// include("backend-starter-api")
// include("backend-starter-service")
