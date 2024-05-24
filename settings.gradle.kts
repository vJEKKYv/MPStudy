pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MPStudy"
include(":app")
include(":ch11-4")
include(":11-4-1")
include(":11-4-2")
include(":11-5")
include(":11-6")
include(":10-2")
include(":10-3")
include(":10-3_1")
include(":10-4")
include(":10-5")
include(":12-1")
include(":12-4")
include(":14-1")
include(":14-2")
include(":15-1")
include(":15-2")
include(":15-3")
include(":16-1")
include(":16-2")
