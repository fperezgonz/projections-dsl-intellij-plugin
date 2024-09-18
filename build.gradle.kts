plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "solutions.sulfura"
version = "1.1-RELEASE"

apply(from = "config/settings.env.gradle.kts")

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://gitlab.com/api/v4/projects/46985246/packages/maven")
        credentials(HttpHeaderCredentials::class) {
            name = properties.getOrDefault("mavenTokenName", "Job-Token").toString()
            value = properties.getOrDefault("mavenTokenValue", System.getenv("CI_JOB_TOKEN")).toString()
        }
        authentication {
            create<HttpHeaderAuthentication>("header")
        }
    }
}

dependencies {
    implementation("solutions.sulfura:gen-d-api:1.1.0-RELEASE")
    implementation("solutions.sulfura:gen-d-projections-dsl:1.1.0-RELEASE")
    implementation("io.vavr:vavr:0.10.4")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.2.6")
    type.set("IC") // Target IDE Platform
    plugins.set(listOf("com.intellij.java", "org.intellij.intelliLang"))
}

sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["main"].java.exclude("solutions/sulfura/plugin/manualtests/**")

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv(file("config/chain.crt").readText()))
        privateKey.set(System.getenv(file("config/private.pem").readText()))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
