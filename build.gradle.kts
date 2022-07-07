import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id ("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "io.github.rokuosan"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17
val mcVersion = "1.18.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    // Ktor
    implementation("io.ktor:ktor-server-core:2.0.3")
    implementation("io.ktor:ktor-server-netty:2.0.3")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    compileOnly("org.spigotmc:spigot-api:${mcVersion}-R0.1-SNAPSHOT")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClass.set("io.github.rokuosan.ktorintegration.AppKt")
}

tasks.withType<KotlinCompile>{
    kotlinOptions{
        jvmTarget = "17"
    }
}

tasks.withType<ProcessResources>{
    val props = mapOf("version" to version)

    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml"){
        expand(props)
    }
}