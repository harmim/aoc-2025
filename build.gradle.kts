plugins {
    kotlin("jvm") version "2.2.21"
    application
}

kotlin {
    jvmToolchain(24)
}

application {
    mainClass.set("com.harmim.aoc2025.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
}
