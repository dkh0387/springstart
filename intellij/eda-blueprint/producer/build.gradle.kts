import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

/**
 * VERY IMPORTANT: this one we have to set in order to make the app runnable.
 * Make sure the class reference is not like <filename>.kt, but <filename>Kt
 * Further we need META-INF/MANIFEST.MF, where we point to the Main-Class for executing JAR.
 */
springBoot {
    mainClass.set("de.dkh.producer.KafkaProducerApplicationKt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka") // if we do not provide a version Spring boot will automatically peak the right one
    // We need this to read wikipedia recent change events from web
    implementation("com.launchdarkly:okhttp-eventsource:2.5.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // for translating JSON into POJO
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("io.mockk:mockk:1.10.4")
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
