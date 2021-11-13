import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project

plugins {
    id("org.beryx.runtime") version "1.12.7"
    id("org.flywaydb.flyway") version "5.2.4"
    kotlin("jvm") version "1.5.31"
    application
}

group = "me.ramblball"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

flyway {
    url = System.getenv("POSTGRES_HOST_ALIAS")
    user = System.getenv("POSTGRES_USER")
    password = System.getenv("POSTGRES_PASSWORD")
    baselineOnMigrate = true
    locations = arrayOf("filesystem: resources / db / migration")
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    implementation("com.zaxxer:HikariCP:5.0.0")
    implementation("org.postgresql:postgresql:42.2.24.jre7")

    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}

application {
    mainClass.set("ServerKt")
}