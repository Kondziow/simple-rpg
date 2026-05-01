plugins {
    id("java")
    id("application")
}

group = "wojtanowski.konrad"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Lombok - Dependency & Annotation Processor
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")

    // Lombok dla testów (opcjonalne, jeśli używasz go w testach)
    testCompileOnly("org.projectlombok:lombok:1.18.46")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.46")

    // JUnit
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("wojtanowski.konrad.Main")
}
