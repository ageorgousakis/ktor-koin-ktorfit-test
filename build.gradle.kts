import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktor)
    alias(libs.plugins.ktorfit)
}

group = "test"
version = "0.0.1"

application {
    mainClass.set("test.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

kotlin {
//    sourceSets.all {
//        languageSettings {
//            languageVersion = "2.0"
//        }
//    }
}

ktorfit {
}

ksp {
//    arg("KOIN_CONFIG_CHECK", "true")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "${JavaVersion.VERSION_17}"
        }
    }

//    withType<KotlinJvmCompile> {
//        compilerOptions {
//            languageVersion = KotlinVersion.KOTLIN_2_0
//        }
//    }

    withType<Test> {
        useJUnitPlatform()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }
}


dependencies {
    implementation(libs.bundles.ktor.server)
    implementation(libs.ktor.server.netty)

    implementation(libs.bundles.ktor.client)
//    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.apache5)

    implementation(libs.bundles.ktor.koin)

//    implementation(libs.bundles.ktor.arrow)

//    implementation(libs.hoplite.core)
//    implementation(libs.hoplite.yaml)

//    implementation(libs.bundles.tegral.openapi)

//    implementation(libs.mongodb.driver)

//    implementation(libs.bson.kotlinx)
//    implementation(libs.ks3.jdk)
//    implementation(libs.kotlinx.datetime)

//    implementation(libs.bundles.cohort)
//    implementation(libs.cohort.kafka)
//    implementation(libs.cohort.lettuce)

    implementation(libs.logback.classic)
//    implementation(libs.kotlin.logging)

//    implementation(libs.ktor.i18n)

//    implementation(libs.kotlin.kafka)

    implementation(libs.ktorfit.light)

//    implementation(libs.lettuce)

    ksp(libs.ktorfit.ksp)
    ksp(libs.koin.ksp.compiler)

    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.ktor.server.tests.jvm)
}
