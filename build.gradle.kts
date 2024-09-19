plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    testImplementation("com.code-intelligence:jazzer-api:0.0.0-dev")
    testImplementation("com.code-intelligence:jazzer-junit:0.0.0-dev")
    testImplementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.8")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}