/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

dependencyResolutionManagement {
    enableFeaturePreview("VERSION_CATALOGS")
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}