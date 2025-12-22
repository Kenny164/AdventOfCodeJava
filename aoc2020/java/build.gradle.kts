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
    implementation(project(":aocCommon"))
    implementation("commons-io:commons-io:2.21.0")
    testImplementation(platform("org.junit:junit-bom:6.1.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass.set("com.peeekay.aoc2020.java.Main")
}

sourceSets {
    main {
        resources.srcDirs(
            "src/main/resources",
            "../resources"
        )
    }
}
