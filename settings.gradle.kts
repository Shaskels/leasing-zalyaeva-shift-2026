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

rootProject.name = "leasing-zalyaeva-shift-2026"
include(":app")
include(":component:uicomponent")
include(":feature:carList")
include(":feature:carDetails")
include(":feature:rentCar")
include(":shared:car")
include(":shared:rent")
include(":shared:network")
 