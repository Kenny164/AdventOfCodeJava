plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "AdventOfCodeJava"
include(":aoc2015:java",
    ":aoc2020:java", ":aoc2020:kotlin",
    ":aoc2021:kotlin",
    ":aoc2022:java", ":aoc2022:kotlin",
    ":aoc2023:java",
    ":aoc2024:kotlin", ":aoc2024:java",
    ":aoc2025:java")
include(":aocCommon")
