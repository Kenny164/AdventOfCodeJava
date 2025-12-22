plugins {
    kotlin("jvm") version "2.3.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":aocCommon"))
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
//
//dependencies {
//    implementation("org.jetbrains.kotlin:kotlin-stdlib")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0-M1")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.0-M1")
//}

application {
    mainClass.set("com.peeekay.aoc2024.kotlin.MainKt")
}

sourceSets {
    main {
        resources.srcDirs(
            "src/main/resources",
            "../resources"
        )
    }
}
