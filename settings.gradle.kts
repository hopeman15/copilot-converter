rootProject.name = "copilot-converter"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/dependencies.versions.toml"))
        }
    }
}
