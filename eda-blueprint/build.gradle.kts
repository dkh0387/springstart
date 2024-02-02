import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("application")
    kotlin("jvm") version "1.7.22" apply false
    kotlin("plugin.spring") version "1.7.22" apply false
}

allprojects {
    group = "de.dkh"
    version = "1.0.0"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    buildscript {
        repositories {
            mavenCentral()
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
    subprojects {
        repositories {
            mavenCentral()
        }

        apply {
            plugin("io.spring.dependency-management")
        }
    }
}
