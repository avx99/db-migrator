plugins {
	java
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.7"
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


publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/avx99/db-migrator")
			credentials {
				username = project.findProperty("gpr.username") as String? ?: System.getenv("USERNAME")
				password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN")
			}
		}
	}
	publications {
		create<MavenPublication>("gpr") {
			from(components["java"])
			groupId = project.group.toString()
			artifactId = project.findProperty("artifactId") as String
			version = project.version.toString()
		}
	}
}

tasks.register("printVersion") {
	doLast {
		println(project.version)
	}
}