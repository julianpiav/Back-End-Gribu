plugins {
	java
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "co.edu.unisabana"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt:0.9.0")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	compileOnly ("org.projectlombok:lombok:1.18.24")
	annotationProcessor ("org.projectlombok:lombok:1.18.24")
	implementation("com.mysql:mysql-connector-j:8.1.0")
	implementation ("com.sendinblue:sib-api-v3-sdk:7.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
