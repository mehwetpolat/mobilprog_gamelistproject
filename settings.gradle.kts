pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")  // JitPack repository ekledik
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")  // JitPack repository ekledik
    }
}

rootProject.name = "FinalProject_MobilProgGameList"
include(":app")