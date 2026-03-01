plugins {
    java
    application
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"

}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        vendor = JvmVendorSpec.ADOPTIUM
    }
}

dependencies {
    implementation("solutions.sulfura:hyperkit-spring-boot-starter:6.2.2-RELEASE")
    implementation("org.jspecify:jspecify:1.0.0")
}