plugins {
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

dependencies {
    implementation(project(":aocCommon"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M1")
}

application {
    mainClass.set("com.peeekay.aoc2025.java.Main")
}

tasks.test {
    useJUnitPlatform()
}
