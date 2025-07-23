plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij.platform") version "2.6.0"
}

group = "solutions.sulfura"
version = "1.4-RELEASE"

apply(from = "config/env.gradle.kts")

repositories {
    mavenLocal()
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
    maven {
        url = uri("https://public-package-registry.sulfura.solutions/")
    }
}

dependencies {
    implementation("solutions.sulfura:hyperkit-dto-api:5.0.0-SNAPSHOT")
    implementation("solutions.sulfura:hyperkit-projections-dsl:5.0.0-SNAPSHOT")

    intellijPlatform {
        create("IC", "2025.1.3")
        bundledPlugins("com.intellij.java", "org.intellij.intelliLang")
    }
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
}

// Configure plugin metadata
intellijPlatform {
    pluginConfiguration {
        // In v2, we need to configure the plugin.xml content
        id.set("solutions.sulfura.projections-dsl-intellij-plugin")
        name.set("Projections Dsl")
        description.set("Provides syntax highlighting, autocompletion and navigation for hyperkit DtoProjectionSpec annotations")

        // Compatibility range
        ideaVersion {
            sinceBuild.set("251")
        }
    }

    signing {
        certificateChain.set(System.getenv(file("config/chain.crt").readText()))
        privateKey.set(System.getenv(file("config/private.pem").readText()))
        password.set(project.properties.getOrDefault("PRIVATE_KEY_PASSWORD", "").toString())
    }

    publishing {
        token.set(project.properties.getOrDefault("PUBLISH_TOKEN", "").toString())
    }

}
