enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KMM_Chat"
include(":androidApp")

include(":features:forum")
include(":features:chat")

include(":utilities:networking")
include(":utilities:logging")
