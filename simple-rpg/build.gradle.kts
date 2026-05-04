// Build configuration for Simple RPG application

plugins {
	java
	id("org.springframework.boot") version "4.0.6"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "wojtanowski.konrad"
version = "0.0.1-SNAPSHOT"

// Java configuration
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

// Repository configuration
repositories {
	mavenCentral()
}

// Dependencies
dependencies {
	// Spring Data JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Spring validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// H2 in-memory database
	runtimeOnly("com.h2database:h2")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.h2database:h2")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
}

// Test configuration
tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
	standardInput = System.`in`
}
