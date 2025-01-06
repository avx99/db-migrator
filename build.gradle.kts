plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.7"
	`java-library`
	`maven-publish`

}

group = project.findProperty("group") as String? ?: "com.avx"
version = project.findProperty("version") as String? ?: "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.hibernate:hibernate-core:6.2.1.Final")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql:42.5.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.apache.commons:commons-lang3:3.17.0")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter:3.4.0")
}


group = "com.avx99"
version = "1.0.0"

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}
	repositories {
		mavenLocal()
	}
}

tasks.register("printVersion") {
	doLast {
		println(project.version)
	}
}

tasks.getByName<Jar>("jar") {
	archiveClassifier.set("")
}

tasks.bootJar {
	enabled = false
}
