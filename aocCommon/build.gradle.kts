plugins {
    id("java")
}

group = "com.peeekay.aocCommon"
version = "unspecified"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        resources.srcDirs(
            "../resources"
        )
    }
}