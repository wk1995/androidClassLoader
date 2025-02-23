pluginManagement {
    repositories {
        maven ( url ="https://jitpack.io" )
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ( url ="https://jitpack.io" )
        google()
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "androidClassLoader"
include(":app")
//include(":demoClass")
//include(":demoClass2")
