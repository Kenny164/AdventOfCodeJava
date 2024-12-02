plugins {
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("commons-io:commons-io:2.18.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M1")
}

application {
    mainClass.set("com.peeekay.aoc2023.java.Main")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        resources.srcDirs(
            "src/main/resources",
            "../resources"
        )
    }
}
